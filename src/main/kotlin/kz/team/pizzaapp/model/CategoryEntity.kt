package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.CategoryResponseDTO
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Category : LongIdTable() {
    val name = varchar("name", 50)
    val description = text("description")
    val isActive = bool("is_active").default(true)
}

class CategoryEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CategoryEntity>(Category)

    var name by Category.name
    var description by Category.description
    var isActive by Category.isActive

    fun getDTO() = CategoryResponseDTO(
        id = this.id.value,
        name = this.name,
        description = this.description,
        isActive = this.isActive
    )
}