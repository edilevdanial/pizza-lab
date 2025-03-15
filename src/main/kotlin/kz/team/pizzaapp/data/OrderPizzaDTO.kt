package kz.team.pizzaapp.data


enum class PizzaSize {
    SMALL, MEDIUM, LARGE
}
data class OrderPizzaRequestDTO(
    val pizzaId: Long,
    val quantity: Int,
    val size: PizzaSize
)

data class OrderPizzaResponseDTO(
    val pizzaId: Long,
    val quantity: Int,
    val size: PizzaSize
)