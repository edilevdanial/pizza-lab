package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.OrderPizzaResponseDTO
import kz.team.pizzaapp.data.PizzaSize
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object OrderPizza : LongIdTable("order_pizzas") {
    val orderId = long("order_id").references(Orders.id)
    val pizzaId = long("pizza_id")
    val quantity = integer("quantity")
    val size = varchar("size", 50)
}

class OrderPizzaEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<OrderPizzaEntity>(OrderPizza)

    var orderId by OrderPizza.orderId
    var pizzaId by OrderPizza.pizzaId
    var quantity by OrderPizza.quantity
    var size by OrderPizza.size

    fun getDTO() = OrderPizzaResponseDTO(
        pizzaId = this.pizzaId,
        quantity = this.quantity,
        size = PizzaSize.valueOf(this.size)
    )
}