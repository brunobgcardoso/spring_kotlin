package com.braga.demo

import com.braga.demo.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class DemoApplication{
	companion object {
		val initialPromocoes = arrayOf(
			Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 4999.99),
			Promocao(2, "Maravilhosa viagem em Família", "Serra Gaúcha", true, 3500.00),
			Promocao(3, "Contato com a natureza", "Foz do Iguaçu", true, 6000.86),
			Promocao(4, "Relaxamento total", "Caldas Novas", true, 2000.00)
		)

		@Bean
		fun promocoes() = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
