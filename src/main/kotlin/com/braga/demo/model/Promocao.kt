package com.braga.demo.model

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Entity
data class Promocao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 1,
    val descricao : String = "",
    val local : String = "",
    val isAllInclusive : Boolean = false,
    val preco : Double = 0.0
)