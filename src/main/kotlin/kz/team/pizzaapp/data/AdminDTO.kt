package kz.team.pizzaapp.data

data class AdminDTO(
    val id: Long? = null,
    val username: String,
    val roles: String,
    val isActive: Boolean
)

