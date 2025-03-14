package kz.team.pizzaapp.data;

data class PizzaResponseDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String,
    val image: String,
    val isActive: Boolean
)

data class PizzaRequestDTO(
    val name: String,
    val price: Double,
    val description: String,
    val image: String,
    val isActive: Boolean,
    val categoryId: Long,
    val ingredientsList: List<Long>
)


