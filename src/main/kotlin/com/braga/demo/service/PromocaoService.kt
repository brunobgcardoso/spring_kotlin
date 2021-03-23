package com.braga.demo.service

import com.braga.demo.model.Promocao
import com.braga.demo.repository.interfaces.IPromocaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import com.braga.demo.service.interfaces.IPromocaoService as IPromocaoService

@Component
class PromocaoService(val promocaoRepository: IPromocaoRepository): IPromocaoService {

    @CacheEvict("promocoes", allEntries = true)
    override fun create(promocao: Promocao) {
        promocaoRepository.save(promocao)
    }

    override fun getById(id: Long): Promocao? = promocaoRepository.findById(id).orElseGet(null)

    @CacheEvict("promocoes", allEntries = true)
    override fun delete(id: Long) {
        promocaoRepository.deleteById(id)
    }

    @CacheEvict("promocoes", allEntries = true)
    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        listOf()

    @Cacheable("promocoes")
    override fun getAll(start: Int, size: Int): List<Promocao> {
        var page = PageRequest.of(start, size, Sort.by("local").ascending())
        return promocaoRepository.findAll(page).toList()
    }

    override fun count(): Long {
        return promocaoRepository.count()
    }

    override fun getAllSortedByLocal(): List<Promocao> {
        return promocaoRepository.findAll(Sort.by("local").descending()).toList()
    }

    override fun getAllByPreçoMaiorQue(preco: Double): List<Promocao> {
        return promocaoRepository.findByPrecoMaiorQue(preco)
    }

}