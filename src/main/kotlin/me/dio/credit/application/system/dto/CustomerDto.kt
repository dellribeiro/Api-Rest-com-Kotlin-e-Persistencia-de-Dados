package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import me.dio.credit.application.system.model.Address
import me.dio.credit.application.system.model.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotBlank(message = "Name must not be blank")
    @field:Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+\$", message = "Name must not contain numbers or special characters")
    val firstName: String,

    @field:NotBlank(message = "Last Name must not be blank")
    @field:Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+\$", message = "Last name must not contain numbers or special characters")
    val lastName: String,

    @field:NotBlank(message = "CPF must not be blank")
    @field:CPF(message = "Insert a valid CPF")
    val cpf: String,

    @field:NotNull(message = "Income must not be blank")
    val income: BigDecimal,

    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Insert a valid email")
    val email: String,

    @field:NotBlank(message = "Password must not be blank")
    val password: String,

    @field:NotBlank(message = "Zip Code must not be blank")
    val zipCode: String,

    @field:NotBlank(message = "Street must not be blank")
    val street: String
) {

    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )

}
