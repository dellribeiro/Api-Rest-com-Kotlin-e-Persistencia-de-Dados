package me.dio.credit.application.system.utils

import me.dio.credit.application.system.dto.CreditDto
import me.dio.credit.application.system.dto.CustomerDto
import me.dio.credit.application.system.dto.CustomerUpdateDto
import me.dio.credit.application.system.enumeration.Status
import me.dio.credit.application.system.model.Address
import me.dio.credit.application.system.model.Credit
import me.dio.credit.application.system.model.Customer
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

internal fun buildCustomer(
    firstName: String = "NameTest",
    lastName: String = "LastNameTest",
    cpf: String = "66787843160",
    email: String = "test@email.com",
    password: String = "1234",
    income: BigDecimal = BigDecimal.valueOf(1000.0),
    zipCode: String = "12345",
    street: String = "Street Test",
    id: Long = 1L
): Customer = Customer(
    firstName = firstName,
    lastName = lastName,
    cpf = cpf,
    email = email,
    password = password,
    income = income,
    address = Address(
        zipCode = zipCode,
        street = street,
    ),
    id = id
)

internal fun buildCustomerDto(
    firstName: String = "NameTest",
    lastName: String = "LastNameTest",
    cpf: String = "66787843160",
    email: String = "test@email.com",
    password: String = "1234",
    income: BigDecimal = BigDecimal.valueOf(1000.0),
    zipCode: String = "12345",
    street: String = "Street Test",
): CustomerDto = CustomerDto(
    firstName = firstName,
    lastName = lastName,
    cpf = cpf,
    email = email,
    password = password,
    income = income,
    zipCode = zipCode,
    street = street,
)

internal fun buildCustumerUpdateDto(
    firstName: String = "NameUpdate",
    lastName: String = "LastNameUpdate",
    income: BigDecimal = BigDecimal.valueOf(5000.0),
    zipCode: String = "45678",
    street: String = "Street Update",
): CustomerUpdateDto = CustomerUpdateDto(
    firsName = firstName,
    lastName = lastName,
    income = income,
    zipCode = zipCode,
    street = street
)

internal fun buildCredit(
    creditCode: UUID = UUID.randomUUID(),
    creditValue: BigDecimal = BigDecimal.valueOf(500.0),
    dayFirstlnstallment: LocalDate = LocalDate.now().plusMonths(3),
    numberOfInstallments: Int = 4,
    status: Status = Status.IN_PROGRESS,
    customer: Customer = buildCustomer(),
    id: Long = 1L
): Credit {
    return Credit(
        creditCode = creditCode,
        creditValue = creditValue,
        dayFirstlnstallment = dayFirstlnstallment,
        numberOfInstallments = numberOfInstallments,
        status = status,
        customer = customer,
        id = id
    )
}

internal fun buildCreditDto(
    creditValue: BigDecimal = BigDecimal.valueOf(500.0),
    dayFirstlnstallment: LocalDate = LocalDate.now().plusMonths(6),
    numberOfInstallments: Int = 4,
    customerId: Long = 1
): CreditDto = CreditDto(
    creditValue = creditValue,
    dayFirstlnstallment = dayFirstlnstallment,
    numberOfInstallments = numberOfInstallments,
    customerId = customerId
)