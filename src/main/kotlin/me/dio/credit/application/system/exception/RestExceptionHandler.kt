package me.dio.credit.application.system.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val error: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach { erro: ObjectError ->
            val fieldName: String = (erro as FieldError).field
            val messageError: String? = erro.defaultMessage
            error[fieldName] = messageError
        }
        return ResponseEntity(
            ExceptionDetails(
                title = "Bad Request! Consult the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = error
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerDataAccessException(ex: DataAccessException): ResponseEntity<ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            ExceptionDetails(
                title = "Conflict! Consult the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.CONFLICT.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            )
        )
    }


    @ExceptionHandler(BusinessException::class)
    fun handlerBadRequest(ex: BusinessException): ResponseEntity<ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDetails(
                title = "Bad Request! Consult the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            )
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDetails(
                title = "Bad Request! Consult the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            )
        )
    }
}