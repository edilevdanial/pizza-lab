package kz.team.pizzaapp.data

data class OrderResponseDTO(
    val id: Long,
    val userId: Long,
    val orderDate: String,
    val totalAmount: Double,
    val status: String
)

data class OrderRequestDTO(
    val userId: Long,
    val totalAmount: Double
)