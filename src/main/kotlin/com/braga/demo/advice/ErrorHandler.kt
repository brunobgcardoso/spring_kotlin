package com.braga.demo.advice

import com.braga.demo.exception.PromocaoNotFoundException
import com.braga.demo.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(request : HttpServletRequest, response: HttpServletResponse, exception :Exception):
    ResponseEntity<ErrorMessage> {
         return ResponseEntity(ErrorMessage("JSON Error", exception.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
    }

    //Evitar usar para exceção de negócio para não ficar parecendo com o comportamento do Go To
    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandler(request: HttpServletRequest, response: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promoção não encontrada", exception.message ?: "Invalid promotion"), HttpStatus.NOT_FOUND)
    }
}