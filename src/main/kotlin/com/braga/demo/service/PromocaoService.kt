package com.braga.demo.service

import com.braga.demo.model.Promocao
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import com.braga.demo.service.interfaces.IPromocaoService as IPromocaoService

@Component
class PromocaoService: IPromocaoService {
    companion object {
        val initialPromocoes = arrayOf(
            Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 4999.99),
            Promocao(2, "Maravilhosa viagem em Família", "Serra Gaúcha", true, 3500.00),
            Promocao(3, "Contato com a natureza", "Foz do Iguaçu", true, 6000.86),
            Promocao(4, "Relaxamento total", "Caldas Novas", true, 2000.00)
        )

    }

    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun getById(id: Long): Promocao? = promocoes[id]

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        promocoes.filter{
            it.value.local.contains(local, true)
        }.map(Map.Entry<Long,Promocao>::value).toList()

}