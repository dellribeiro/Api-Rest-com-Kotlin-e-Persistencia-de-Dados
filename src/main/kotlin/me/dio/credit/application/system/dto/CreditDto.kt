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

    @field:NotNull(message = "Invalid First Installment Date")
    @field:Future(message = "Invalid First Installment Date")
    val dayFirstlnstallment: LocalDate,

    @field:NotNull(message = "Invalid Number of Installments")
    @field:Max(value = 48, message = "Number of Installments cannot exceed 48")
    val numberOfInstallments: Int,

    @field:NotNull(message = "Invalid Customer Id")
    val customerId: Long
) {
    init{
        val currentDate: LocalDate = LocalDate.now()
        val maxDate: LocalDate = currentDate.plusMonths(3)
        if(dayFirstlnstallment.isAfter(maxDate))
            throw IllegalArgumentException("Invalid First Installment Date")
    }
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstlnstallment = this.dayFirstlnstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}