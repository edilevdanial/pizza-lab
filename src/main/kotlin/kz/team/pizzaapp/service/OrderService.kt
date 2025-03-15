package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.OrderRequestDTO
import kz.team.pizzaapp.data.OrderResponseDTO
import kz.team.pizzaapp.data.PizzaSize
import kz.team.pizzaapp.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val pizzaService: PizzaService,
    val orderPizzaService: OrderPizzaService
) {

    fun saveOrder(orderRequestDTO: OrderRequestDTO, userId: Long): OrderResponseDTO {
        var totalAmount = 0.0
        orderRequestDTO.orderPizza.forEach {
            var pizzaPrice = pizzaService.getPizzaById(it.pizzaId)?.price
            if (pizzaPrice != null) {
                pizzaPrice = when (it.size) {
                    PizzaSize.SMALL -> pizzaPrice.times(0.75)
                    PizzaSize.LARGE -> pizzaPrice.times( 1.5)
                    else -> pizzaPrice
                }
                totalAmount += pizzaPrice * it.quantity
            } else {
                throw Exception("Pizza with id ${it.pizzaId} not found")
            }
        }

        val savedOrder = orderRepository.saveOrder(orderRequestDTO, totalAmount, userId)
        return savedOrder.getDTO()
    }

    fun getOrdersByUserId(userId: Long): List<OrderResponseDTO> {
        return orderRepository.getOrdersByUserId(userId).map {
            it.getDTO()
//            it.getDTO(orderPizzaService.getOrderPizzasByOrderId(it.id.value))
        }
    }

    fun updateOrderStatus(orderId: Long, status: String) {
        orderRepository.updateOrderStatus(orderId, status)
    }
}