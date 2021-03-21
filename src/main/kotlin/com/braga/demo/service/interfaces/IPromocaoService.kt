package com.braga.demo.service.interfaces

import com.braga.demo.model.Promocao

interface IPromocaoService {
    fun create(promocao: Promocao)
    fun getById(id : Long): Promocao?
    fun delete(id: Long)
    fun update(id: Long, promocao : Promocao)
    fun searchByLocal (local: String) : List<Promocao>
    fun getAll (start: Int, size: Int) : List<Promocao>
    fun count() : Long
    fun getAllSortedByLocal() : List<Promocao>
    fun getAllByPreçoMaiorQue(preco: Double): List<Promocao>
}