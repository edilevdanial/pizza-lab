package kz.team.pizzaapp.repository

import kz.team.pizzaapp.data.CategoryRequestDTO
import kz.team.pizzaapp.model.CategoryEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class CategoryRepository {

    fun getAll(): List<CategoryEntity> = transaction {
        CategoryEntity.all().toList()
    }


    fun findById(id: Long): CategoryEntity? = transaction {
        CategoryEntity.findById(id)
    }

    fun save(categoryRequestDTO: CategoryRequestDTO): CategoryEntity {
        return transaction {
            CategoryEntity.new {
                this.name = categoryRequestDTO.name
                this.description = categoryRequestDTO.description
            }
        }
    }

    fun update(id: Long, categoryRequestDTO: CategoryRequestDTO): CategoryEntity? {
        return transaction {
            val category = CategoryEntity.findById(id)
            category?.name = categoryRequestDTO.name
            category?.description = categoryRequestDTO.description
            category?.isActive = categoryRequestDTO.isActive
            return@transaction category
        }
    }

    fun deleteById(id: Long) {
        transaction {
            CategoryEntity.findById(id)?.delete()
        }
    }
}