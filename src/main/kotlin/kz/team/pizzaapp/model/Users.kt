package kz.team.pizzaapp.model

import kz.team.pizzaapp.data.UserDTO
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable("users") {
    val username = varchar("username", 100)
    val password = varchar("password_hash", 255)
    val email = varchar("email", 150).uniqueIndex()
    val phone = varchar("phone", 50).uniqueIndex()
    val address = varchar("address", 255)
    val isActive = bool("is_active").default(true)
}

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(Users)

    var username by Users.username
    var password by Users.password
    var email by Users.email
    var phone by Users.phone
    var address by Users.address
    var isActive by Users.isActive

    fun getDTO() = UserDTO(
        id = this.id.value,
        username = this.username,
        email = this.email,
        phone = this.phone,
        address = this.address,
        isActive = this.isActive,
        password = this.password
    )
}