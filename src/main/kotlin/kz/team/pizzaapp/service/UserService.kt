package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.UserCreateDTO
import kz.team.pizzaapp.data.UserDTO
import kz.team.pizzaapp.repository.UserRepository
import kz.team.pizzaapp.utils.PasswordUtil
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getAll() = userRepository.getAll()

    fun save(userDTO: UserCreateDTO): UserDTO {
        userDTO.password = PasswordUtil.encode(userDTO.password)

        return userRepository.save(userDTO).getDTO()
    }

    fun getByPhone(phone: String) = userRepository.getByPhone(phone)?.getDTO()

    fun update(id: Long, userDTO: UserDTO) = userRepository.update(id, userDTO)

    fun updatePassword(id: Long, password: String) = userRepository.updatePassword(id, password)
}