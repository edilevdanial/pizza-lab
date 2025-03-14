package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.IngredientResponseDTO
//import kz.team.pizzaapp.model.PizzaCategories.references
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Ingredient : LongIdTable() {
    val name = varchar("name", 50)
}

class IngredientEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<IngredientEntity>(Ingredient)

    var name by Ingredient.name

    fun getDTO() = IngredientResponseDTO(
        id = this.id.value,
        name = this.name
    )
}

//object PizzaIngredient : Table("Pizza_Ingredients") {
//    val pizzaId = long("pizza_id").references(Pizzas.id, onDelete = ReferenceOption.CASCADE)
//    val ingredientId = long("ingredient_id").references(Ingredient.id, onDelete = ReferenceOption.CASCADE)
//
//    override val primaryKey = PrimaryKey(pizzaId, ingredientId)
//}