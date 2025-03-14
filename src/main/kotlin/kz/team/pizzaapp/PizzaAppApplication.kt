package kz.team.pizzaapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PizzaAppApplication

fun main(args: Array<String>) {
    runApplication<PizzaAppApplication>(*args)
}
