package kz.team.pizzaapp.repository

import kz.team.pizzaapp.model.IngredientEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class IngredientRepository {


    fun getAll(): List<IngredientEntity> = transaction {
        IngredientEntity.all().toList()
    }

    fun findById(id: Long): IngredientEntity? = transaction {
        IngredientEntity.findById(id)
    }

    fun save(name: String) = transaction {
        IngredientEntity.new {
            this.name = name
        }
    }

    fun deleteById(id: Long) = transaction {
        IngredientEntity.findById(id)?.delete()
    }
}