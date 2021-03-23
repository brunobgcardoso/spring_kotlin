package com.braga.demo.controller

import com.braga.demo.model.Cliente
import com.braga.demo.model.Telefone
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class JSONController(private val environment: Environment) {
    @Value("\${spring.profiles.active}")
    lateinit var profile : String

    @GetMapping("/cliente")
    fun getCliente(): Cliente {
        var tel = Telefone("21", "233323232", "Fixo")
        return Cliente(1, "Teste", Date(), tel)
    }
}