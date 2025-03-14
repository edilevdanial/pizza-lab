package kz.team.pizzaapp.repository

import kz.team.pizzaapp.data.OrderRequestDTO
import kz.team.pizzaapp.model.Orders
import kz.team.pizzaapp.model.OrdersEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {

    fun saveOrder(orderRequestDTO: OrderRequestDTO) {
        transaction {
            OrdersEntity.new {
                userId = orderRequestDTO.userId
                totalAmount = orderRequestDTO.totalAmount.toBigDecimal()
            }
        }
    }

    fun getOrdersByUserId(userId: Long): List<OrdersEntity> = transaction {
        OrdersEntity.find { Orders.userId eq userId }.toList()
    }

    fun updateOrderStatus(orderId: Long, status: String) = transaction {
        OrdersEntity.findById(orderId)?.let {
            it.status = status
        }
    }

}