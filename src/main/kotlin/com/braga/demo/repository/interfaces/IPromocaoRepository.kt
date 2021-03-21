package com.braga.demo.repository.interfaces

import com.braga.demo.model.Promocao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface IPromocaoRepository: PagingAndSortingRepository<Promocao, Long> {
    @Query(value= "select p from Promocao p where p.preco >= :preco")
    fun findByPrecoMaiorQue(@Param("preco") preco: Double): List<Promocao>

}