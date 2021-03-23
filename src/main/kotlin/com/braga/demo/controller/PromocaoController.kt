package com.braga.demo.controller

import com.braga.demo.exception.PromocaoNotFoundException
import com.braga.demo.model.ErrorMessage
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
    fun getAll(@RequestParam(required = false, defaultValue = "1") start: Int,
               @RequestParam(required = false, defaultValue = "3") size: Int) : ResponseEntity<List<Promocao>> {
        var promocoes = promocaoService.getAll(start, size)
        var status = if(promocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocoes, status)
    }

    @GetMapping("/sort_local")
    fun getAllByLocal() : ResponseEntity<List<Promocao>> {
        var promocoes = promocaoService.getAllSortedByLocal()
        var status = if(promocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocoes, status)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long): ResponseEntity<Any> {
        var promocao = promocaoService.getById(id)
        return if (promocao != null)
            return ResponseEntity(promocao, HttpStatus.OK)
        else
            ResponseEntity(ErrorMessage("Promoção não encontrada", "Promoção ${id} não localizada!"), HttpStatus.NOT_FOUND)
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

    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String, Long>> =
        ResponseEntity.ok().body(mapOf("count" to promocaoService.count()))

    @GetMapping("/maior_que")
    fun maiorQue(@RequestParam(required = true, defaultValue = "0") preco: Double): ResponseEntity<List<Promocao>> {
        var promocoes = promocaoService.getAllByPreçoMaiorQue(preco)
        var status = if(promocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocoes, status)
    }
}