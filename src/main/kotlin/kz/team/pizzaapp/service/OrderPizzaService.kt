package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.OrderPizzaRequestDTO
import kz.team.pizzaapp.repository.OrderPizzaRepository
import org.springframework.stereotype.Service


@Service
class OrderPizzaService(val orderPizzaRepository: OrderPizzaRepository) {

    fun saveOrderPizza(orderId: Long, orderPizzaRequestDTO: OrderPizzaRequestDTO) {
        orderPizzaRepository.saveOrderPizza(orderId, orderPizzaRequestDTO)
    }

    fun getOrderPizzasByOrderId(orderId: Long) = orderPizzaRepository.getOrderPizzasByOrderId(orderId)
}