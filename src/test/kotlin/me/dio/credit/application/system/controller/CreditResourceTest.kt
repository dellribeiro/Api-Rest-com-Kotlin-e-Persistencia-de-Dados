package me.dio.credit.application.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.dio.credit.application.system.dto.CreditDto
import me.dio.credit.application.system.model.Credit
import me.dio.credit.application.system.model.Customer
import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.utils.buildCredit
import me.dio.credit.application.system.utils.buildCreditDto
import me.dio.credit.application.system.utils.buildCustomer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*



@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CreditResourceTest {

    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var creditRepository: CreditRepository
    @Autowired private lateinit var customerRepository: CustomerRepository
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object{
        const val URL: String = "/api/credits"
    }

    @BeforeEach fun setup() = creditRepository.deleteAll()
    @AfterEach fun tearDown() = creditRepository.deleteAll()

    @Test
    fun `should save credit and return 201 status`() {
        val customerId: Long = 1
        val customer: Customer = customerRepository.save(buildCustomer(id = customerId))
        val creditDto: CreditDto = buildCreditDto(customerId = customerId)
        val valueAsString: String = objectMapper.writeValueAsString(creditDto)

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value("500.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfInstallment").value(4))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save credit and return 400 status`() {
        val customerId: Long = 42
        val creditDto: CreditDto = buildCreditDto(customerId = customerId)
        val valueAsString: String = objectMapper.writeValueAsString(creditDto)

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class me.dio.credit.application.system.exception.BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find all credits by customer id and return 200 status`() {
        val customer: Customer = customerRepository.save(buildCustomer())
        val credit1: Credit = creditRepository.save(buildCredit(customer = customer))
        val credit2: Credit = creditRepository.save(buildCredit(customer = customer))

        mockMvc.perform(MockMvcRequestBuilders.get("$URL?customerId=${customer.id}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should return an empty list if customer not have credits and return 200 status`() {
        val customer: Customer = customerRepository.save(buildCustomer())

        mockMvc.perform(MockMvcRequestBuilders.get("$URL?customerId=${customer.id}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json("[]"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find credit by credit code and return 200 status`() {
        val customer: Customer = customerRepository.save(buildCustomer())
        val creditCode: UUID = UUID.randomUUID()
        val credit: Credit = creditRepository.save(buildCredit(customer = customer, creditCode = creditCode))

        mockMvc.perform(MockMvcRequestBuilders.get("$URL/$creditCode?customerId=${customer.id}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not find credit by credit code and return 400 status`() {
        val customer: Customer = customerRepository.save(buildCustomer())
        val creditCode: UUID = UUID.randomUUID()
        val credit: Credit = creditRepository.save(buildCredit(customer = customer))

        mockMvc.perform(MockMvcRequestBuilders.get("$URL/$creditCode?customerId=${customer.id}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class me.dio.credit.application.system.exception.BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }


}