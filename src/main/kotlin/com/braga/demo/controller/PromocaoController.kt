package com.braga.demo.controller

import com.braga.demo.model.Promocao
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PromocaoController {
    @RequestMapping(value = arrayOf("/helloWorld"), method = arrayOf(RequestMethod.GET))
    fun helloWorld():String{
        return "Hello World"
    }

    @RequestMapping(value = ["/promocoes"], method = [RequestMethod.GET])
    fun getPromocao() :Promocao {
        var p = Promocao(1,"É Nóis", "RJ", true,1000.00)
        return p
    }
}