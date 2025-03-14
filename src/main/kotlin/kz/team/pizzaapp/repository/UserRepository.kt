package kz.team.pizzaapp.repository

import kz.team.pizzaapp.data.UserCreateDTO
import kz.team.pizzaapp.data.UserDTO
import kz.team.pizzaapp.model.UserEntity
import kz.team.pizzaapp.model.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    fun getAll(): List<UserEntity> = transaction {
        UserEntity.all().toList()
    }

    fun save(userDTO: UserCreateDTO) = transaction {
        UserEntity.new {
            this.username = userDTO.username
            this.email = userDTO.email
            this.phone = userDTO.phone
            this.address = userDTO.address
            this.password = userDTO.password
        }
    }

    fun update(id: Long, userDTO: UserDTO) = transaction {
        UserEntity.findByIdAndUpdate(id) {
            it.username = userDTO.username
            it.email = userDTO.email
            it.phone = userDTO.phone
            it.address = userDTO.address
            it.isActive = userDTO.isActive
        } ?: return@transaction null
    }

    fun updatePassword(id: Long, password: String) = transaction {
        UserEntity.findByIdAndUpdate(id) {
            it.password = BCryptPasswordEncoder().encode(password)
        } ?: return@transaction null
    }

    fun getByPhone(phone: String) = transaction {
        UserEntity.find { Users.phone eq phone }.firstOrNull()
    }
}