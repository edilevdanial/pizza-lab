package kz.team.pizzaapp.controller

import kz.team.pizzaapp.data.AdminDTO
import kz.team.pizzaapp.data.UserPrincipal
import kz.team.pizzaapp.service.AdminService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/admin")
class AdminController(val adminService: AdminService) {

    @PostMapping
    fun saveAdmin(@RequestBody adminDTO: AdminDTO) {
        adminService.saveAdmin(adminDTO)
    }

    @GetMapping
    fun getAdmins(): List<AdminDTO> {
        return adminService.getAdmins()
    }

    @PutMapping
    fun updateAdmin(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody adminDTO: AdminDTO
    ) {
        adminService.updateAdmin(userPrincipal.userId, adminDTO)
    }
}