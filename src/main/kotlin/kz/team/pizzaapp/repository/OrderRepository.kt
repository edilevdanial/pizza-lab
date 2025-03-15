package kz.team.pizzaapp.repository

import kz.team.pizzaapp.data.OrderRequestDTO
import kz.team.pizzaapp.model.Orders
import kz.team.pizzaapp.model.OrdersEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(val orderPizzaRepository: OrderPizzaRepository) {

    fun saveOrder(orderRequestDTO: OrderRequestDTO, totalAmount: Double ,userId: Long): OrdersEntity {
        return transaction {
          val d =  OrdersEntity.new {
                this.userId = userId
                this.totalAmount = totalAmount.toBigDecimal()
            }

            orderRequestDTO.orderPizza.forEach {
                orderPizzaRepository.saveOrderPizza(d.id.value, it)
            }
            return@transaction d
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