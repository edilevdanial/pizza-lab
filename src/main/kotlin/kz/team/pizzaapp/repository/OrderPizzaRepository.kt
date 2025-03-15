package kz.team.pizzaapp.repository;

import kz.team.pizzaapp.data.OrderPizzaRequestDTO
import kz.team.pizzaapp.model.OrderPizza
import kz.team.pizzaapp.model.OrderPizzaEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository;

@Repository
class OrderPizzaRepository {

    fun saveOrderPizza(orderId: Long, orderPizzaRequestDTO: OrderPizzaRequestDTO) {
        transaction {
            OrderPizzaEntity.new {
                this.orderId = orderId
                this.pizzaId = orderPizzaRequestDTO.pizzaId
                this.quantity = orderPizzaRequestDTO.quantity
                this.size = orderPizzaRequestDTO.size.name
            }
        }
    }

    fun getOrderPizzasByOrderId(orderId: Long) = OrderPizzaEntity.find { OrderPizza.orderId eq orderId }.toList()


}
