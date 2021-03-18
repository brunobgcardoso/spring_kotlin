package com.braga.demo.model

data class Promocao(
        val id : Long,
        val descricao : String,
        val local : String,
        val idAllInclusive : Boolean,
        val preco : Double
)