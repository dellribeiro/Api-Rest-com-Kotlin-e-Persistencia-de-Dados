package me.dio.credit.application.system.repository

import me.dio.credit.application.system.model.Address
import me.dio.credit.application.system.model.Credit
import me.dio.credit.application.system.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired
    lateinit var creditRepository: CreditRepository

    @Autowired
    lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach
    fun setup() {
        customer = testEntityManager.persist(buildCustomer())
        credit1 = testEntityManager.persist(buildCredit(customer = customer))
        credit2 = testEntityManager.persist(buildCredit(customer = customer))

    }

    @Test
    fun `should find credit by credit code`() {
        val fakeCredit1: Credit? = creditRepository.findByCreditCode(credit1.creditCode)
        val fakeCredit2: Credit? = creditRepository.findByCreditCode(credit2.creditCode)

        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit2).isNotNull
        Assertions.assertThat(fakeCredit1).isSameAs(credit1)
        Assertions.assertThat(fakeCredit2).isSameAs(credit2)
    }

    @Test
    fun `should find all credits by customer id`(){
        val customerId: Long = 1
        val creditList: List<Credit> = creditRepository.findAllByCustomer(customerId)

        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(2)
        Assertions.assertThat(creditList).contains(credit1, credit2)
    }

    private fun buildCustomer(
        firstName: String = "NameTest",
        lastName: String = "LastNameTest",
        cpf: String = "28475934625",
        email: String = "caest@email.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Street Test",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
    )

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstlnstallment: LocalDate = LocalDate.now().plusMonths(3),
        numberOfInstallments: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstlnstallment = dayFirstlnstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )
}