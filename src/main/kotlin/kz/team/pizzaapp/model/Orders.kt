package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.OrderResponseDTO
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

object Orders : LongIdTable("orders") {
    val userId = long("user_id").references(Users.id)
    val orderDate = datetime("order_date").default(LocalDateTime.now())
    val totalAmount = decimal("total_amount", 10, 2)
    val status = varchar("status", 50).default("NEW")
}

class OrdersEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<OrdersEntity>(Orders)

    var userId by Orders.userId
    var orderDate by Orders.orderDate
    var totalAmount by Orders.totalAmount
    var status by Orders.status

    fun getDTO() = OrderResponseDTO(
        id = this.id.value,
        userId = this.userId,
        orderDate = this.orderDate.toString(),
        totalAmount = this.totalAmount.toDouble(),
        status = this.status,
        orderPizza = transaction {
            OrderPizzaEntity.find { OrderPizza.orderId eq super.id.value }.map { it.getDTO() }
        }
    )
}