package kz.team.pizzaapp.data

import java.util.Collections

data class OrderResponseDTO(
    val id: Long,
    val userId: Long,
    val orderDate: String,
    val totalAmount: Double,
    val status: String,
    val orderPizza: List<OrderPizzaResponseDTO> = Collections.emptyList()
)

data class OrderRequestDTO(
    val orderPizza: List<OrderPizzaRequestDTO>
)