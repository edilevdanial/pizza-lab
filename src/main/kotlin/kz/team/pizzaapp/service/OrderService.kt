package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.OrderRequestDTO
import kz.team.pizzaapp.data.OrderResponseDTO
import kz.team.pizzaapp.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepository: OrderRepository) {

    fun saveOrder(orderRequestDTO: OrderRequestDTO) {
        orderRepository.saveOrder(orderRequestDTO)
    }

    fun getOrdersByUserId(userId: Long): List<OrderResponseDTO> {
        return orderRepository.getOrdersByUserId(userId).map { it.getDTO() }
    }

    fun updateOrderStatus(orderId: Long, status: String) {
        orderRepository.updateOrderStatus(orderId, status)
    }
}