package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.AdminDTO
import kz.team.pizzaapp.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(val adminRepository: AdminRepository) {

    fun saveAdmin(adminDTO: AdminDTO) {
        adminRepository.saveAdmin(adminDTO)
    }

    fun getAdmins(): List<AdminDTO> {
        return adminRepository.getAdmins().map { it.getDTO() }
    }
    // asdaasdasd


    fun updateAdmin(adminId: Long, adminDTO: AdminDTO) {
        adminRepository.updateAdmin(adminId, adminDTO)
    }
}