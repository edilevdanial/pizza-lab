package kz.team.pizzaapp.data

data class CategoryResponseDTO(
    var id: Long,
    var name: String,
    var description: String,
    var isActive: Boolean
)

data class CategoryRequestDTO(
    var name: String,
    var description: String,
    var isActive: Boolean
)