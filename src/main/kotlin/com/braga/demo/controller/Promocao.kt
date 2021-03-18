package com.braga.demo.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class Promocao {
    @RequestMapping(value = arrayOf("/promocoes"), method = arrayOf(RequestMethod.GET))
    fun helloWorld():String{
        return "Hello World"
    }
}