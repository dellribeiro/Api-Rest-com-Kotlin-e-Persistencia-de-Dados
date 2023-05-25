package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.model.Credit
import me.dio.credit.application.system.model.Customer

import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid Credit Value")
    val creditValue: BigDecimal,

    @field:Future(message = "Invalid First Installment Date")
    @field:Max(value = 90, message = "First Installment Date must be within 3 months")
    val dayFirstlnstallment: LocalDate,

    @field:NotNull(message = "Invalid Number of Installments")
    @field:Max(value = 48, message = "Number of Installments cannot exceed 48")
    val numberOfInstallments: Int,

    @field:NotNull(message = "Invalid Customer Id")
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstlnstallment = this.dayFirstlnstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}