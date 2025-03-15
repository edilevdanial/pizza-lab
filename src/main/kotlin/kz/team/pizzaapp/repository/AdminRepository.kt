package kz.team.pizzaapp.repository

import kz.team.pizzaapp.data.AdminDTO
import kz.team.pizzaapp.model.AdminEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class AdminRepository {

    fun saveAdmin(adminDTO: AdminDTO) {
        transaction {
            AdminEntity.new {
                username = adminDTO.username
                roles = adminDTO.roles
                isActive = adminDTO.isActive
            }
        }
    }

    fun getAdmins(): List<AdminEntity> = transaction {
        AdminEntity.all().toList()
    }

    fun updateAdmin(adminId: Long, adminDTO: AdminDTO) = transaction {
        AdminEntity.findById(adminId)?.let {
            it.isActive = adminDTO.isActive
            it.roles = adminDTO.roles
        }
    }

}