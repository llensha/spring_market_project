package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.dto.OrderDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Order;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.services.OrderService;
import ru.geekbrains.spring.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public List<OrderDto> findAll(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден при вызове списка заказов"));
        return orderService.findAll(user.getUsername()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Заказ с id = %d не найден при оформлении заказа", id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto checkout(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден при оформлении заказа"));
        return orderService.createOrderFromCart(user, address);
    }
}
