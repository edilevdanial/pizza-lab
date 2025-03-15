package kz.team.pizzaapp.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception): ResponseEntity<Any> {
        val response: MutableMap<String, Any?> = HashMap()
        response["timestamp"] = LocalDateTime.now().toString()
        response["message"] = ex.message
        response["status"] = HttpStatus.INTERNAL_SERVER_ERROR.value()
        error("Internal server error ${ex.message}")
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
