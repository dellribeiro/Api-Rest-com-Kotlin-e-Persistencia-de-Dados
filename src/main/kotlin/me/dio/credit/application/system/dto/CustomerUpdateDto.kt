package me.dio.credit.application.system.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.model.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotBlank(message = "Name must not be blank")
    val firsName: String,

    @field:NotBlank(message = "Last Name must not be blank")
    val lastName: String,

    @field:NotNull(message = "Income must not be blank")
    val income: BigDecimal,

    @field:NotBlank(message = "Zip Code must not be blank")
    val zipCode: String,

    @field:NotBlank(message = "Street must not be blank")
    val street: String
) {

    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firsName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }

}
