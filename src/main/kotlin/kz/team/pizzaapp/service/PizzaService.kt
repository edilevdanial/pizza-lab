package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.PizzaRequestDTO
import kz.team.pizzaapp.data.PizzaResponseDTO
import kz.team.pizzaapp.repository.PizzaRepository
import org.springframework.stereotype.Service

@Service
class PizzaService(
    val pizzaRepository: PizzaRepository,
//    val pizzaIngredientService: PizzaIngredientService,
//    val pizzaCategoryService: PizzaCategoryService
) {

    fun getAllPizza(): List<PizzaResponseDTO> {
        return pizzaRepository.getAll().map { it.getDTO() }
    }

    fun getAllActivePizza(): List<PizzaResponseDTO> {
        return pizzaRepository.getAllActive().map { it.getDTO() }
    }

    fun getPizzaById(id: Long): PizzaResponseDTO? {
        return pizzaRepository.findById(id)?.getDTO()
    }

    fun addPizza(pizzaRequestDTO: PizzaRequestDTO): PizzaResponseDTO {
        val pizza = pizzaRepository.save(pizzaRequestDTO).getDTO()
//        pizzaRequestDTO.ingredientsList.forEach { pizzaIngredientService.save(pizza.id, it) }
//        pizzaCategoryService.save(pizza.id, pizzaRequestDTO.categoryId)

        return pizza
    }

    fun updatePizza(id: Long, pizzaRequestDTO: PizzaRequestDTO): PizzaResponseDTO? {
        return pizzaRepository.update(id, pizzaRequestDTO)?.getDTO()
    }

    fun deletePizza(id: Long) {
        pizzaRepository.deleteById(id)
    }
}