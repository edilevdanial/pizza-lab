package kz.team.pizzaapp.controller

import kz.team.pizzaapp.data.PizzaRequestDTO
import kz.team.pizzaapp.service.PizzaService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pizza")
class PizzaController(
    val pizzaService: PizzaService
) {

    @GetMapping
    fun getAllPizza() = pizzaService.getAllPizza()

    @GetMapping("/{id}")
    fun getPizzaById(@PathVariable id: Long) = pizzaService.getPizzaById(id)

    @PostMapping
    fun addPizza(@RequestBody pizzaRequestDTO: PizzaRequestDTO) = pizzaService.addPizza(pizzaRequestDTO)

    @PostMapping("/{id}")
    fun updatePizza(@PathVariable id: Long, @RequestBody pizzaRequestDTO: PizzaRequestDTO) =
        pizzaService.updatePizza(id, pizzaRequestDTO)

    @DeleteMapping("/{id}")
    fun deletePizza(@PathVariable id: Long) = pizzaService.deletePizza(id)
}