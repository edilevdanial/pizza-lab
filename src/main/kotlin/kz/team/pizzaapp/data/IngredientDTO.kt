package kz.team.pizzaapp.data

data class IngredientResponseDTO(
    val id: Long,
    val name: String
)

data class IngredientRequestDTO(
    val name: String
)