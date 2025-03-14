package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.PizzaResponseDTO
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Pizzas : LongIdTable("pizza") {
    val name = varchar("name", 50)
    val price = decimal("price", 10, 2)
    val description = text("description")
    val imageUrl = text("image_url")
    val isActive = bool("is_active").default(true)
}

class PizzaEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PizzaEntity>(Pizzas)

    var name by Pizzas.name
    var price by Pizzas.price
    var description by Pizzas.description
    var imageUrl by Pizzas.imageUrl
    var isActive by Pizzas.isActive

    fun getDTO() = PizzaResponseDTO(
        id = this.id.value,
        name = this.name,
        price = this.price.toDouble(),
        description = this.description,
        image = this.imageUrl,
        isActive = this.isActive
    )
}

//object PizzaCategories : Table("pizza_categories") {
//    val pizzaId = long("pizza_id").references(Pizzas.id, onDelete = ReferenceOption.CASCADE)
//    val categoryId = long("category_id").references(Category.id, onDelete = ReferenceOption.CASCADE)
//
//    override val primaryKey = PrimaryKey(pizzaId, categoryId)
//}
