package com.braga.demo.controller

import com.braga.demo.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "") local: String) =
        promocoes.filter {
            it.value.local.contains(local, true)
        }.map(Map.Entry<Long, Promocao>::value).toList()

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long) = promocoes[id]

    @PostMapping()
    fun create(@RequestBody promocao : Promocao){
        promocoes[promocao.id] = promocao
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long){
        promocoes.remove(id)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao){
        promocoes.remove(id)
        promocoes[id] = promocao
    }
}