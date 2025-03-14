package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.CategoryRequestDTO
import kz.team.pizzaapp.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(val categoryRepository: CategoryRepository) {

    fun getAll() = categoryRepository.getAll()

    fun findById(id: Long) = categoryRepository.findById(id)

    fun save(categoryRequestDTO: CategoryRequestDTO) = categoryRepository.save(categoryRequestDTO)

    fun update(id: Long, categoryRequestDTO: CategoryRequestDTO) = categoryRepository.update(id, categoryRequestDTO)

    fun deleteById(id: Long) = categoryRepository.deleteById(id)

}