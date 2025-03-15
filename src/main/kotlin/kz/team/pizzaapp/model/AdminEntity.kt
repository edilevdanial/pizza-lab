package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.AdminDTO
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object AdminTable : LongIdTable("admin") {
    val password = varchar("password_hash", 100)
    val username = varchar("username", 255)
    val isActive = bool("is_active").default(true)
    val roles = varchar("roles", 255)
}

class AdminEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<AdminEntity>(AdminTable)

    var password by AdminTable.password
    var username by AdminTable.username
    var roles by AdminTable.roles
    var isActive by AdminTable.isActive

    fun getDTO() = AdminDTO(
        id = this.id.value,
        username = this.username,
        roles = this.roles,
        isActive = this.isActive
    )
}