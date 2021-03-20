package com.braga.demo.controller

import com.braga.demo.model.Promocao
import com.braga.demo.model.RespostaJSON
import com.braga.demo.service.interfaces.IPromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocaoService: IPromocaoService

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "") local: String) : ResponseEntity<List<Promocao>> {
        var promocoes = promocaoService.searchByLocal(local)
        var status = if(promocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocoes, status)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long): ResponseEntity<Promocao?> {
        var promocao = promocaoService.getById(id)
        var status = if (promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocao, status)
    }

    @PostMapping()
    fun create(@RequestBody promocao : Promocao) : ResponseEntity<RespostaJSON>{
        promocaoService.create(promocao)
        var resposta = RespostaJSON("OK", Date())
        return ResponseEntity(resposta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND

        if (promocaoService.getById(id) != null){
            status = HttpStatus.ACCEPTED
            promocaoService.delete(id)
        }

        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND

        if (promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            promocaoService.update(id, promocao)
        }

        return ResponseEntity(Unit, status)
    }
}