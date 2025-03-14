package kz.team.pizzaapp.repository

import kz.team.pizzaapp.data.PizzaRequestDTO
import kz.team.pizzaapp.model.PizzaEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class PizzaRepository {

    fun getAll(): List<PizzaEntity> = transaction { PizzaEntity.all().toList() }

    fun findById(id: Long): PizzaEntity? = transaction { PizzaEntity.findById(id) }

    fun save(pizzaRequestDTO: PizzaRequestDTO) = transaction {
        PizzaEntity.new {
            this.name = pizzaRequestDTO.name
            this.price = pizzaRequestDTO.price.toBigDecimal()
            this.description = pizzaRequestDTO.description
            this.imageUrl = pizzaRequestDTO.image
        }
    }

    fun deleteById(id: Long) = transaction { PizzaEntity.findById(id)?.delete() }

    fun update(id: Long, pizzaRequestDTO: PizzaRequestDTO) = transaction {
        PizzaEntity.findById(id)?.apply {
            this.name = pizzaRequestDTO.name
            this.price = pizzaRequestDTO.price.toBigDecimal()
            this.description = pizzaRequestDTO.description
            this.imageUrl = pizzaRequestDTO.image
            this.isActive = pizzaRequestDTO.isActive
        }
    }

}