package kz.team.pizzaapp.controller

import kz.team.pizzaapp.data.OrderRequestDTO
import kz.team.pizzaapp.data.OrderResponseDTO
import kz.team.pizzaapp.data.UserPrincipal
import kz.team.pizzaapp.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(val orderService: OrderService) {

    @PostMapping
    fun saveOrder(
        @RequestBody orderRequestDTO: OrderRequestDTO,
        @AuthenticationPrincipal
        user: UserPrincipal
    ): ResponseEntity<OrderResponseDTO> {
        return ResponseEntity.accepted().body(orderService.saveOrder(orderRequestDTO, user.userId))
    }

    @GetMapping
    fun getOrdersByUserId(@AuthenticationPrincipal user: UserPrincipal): ResponseEntity<List<OrderResponseDTO>> {
        return ResponseEntity.ok(orderService.getOrdersByUserId(user.userId))
    }

}