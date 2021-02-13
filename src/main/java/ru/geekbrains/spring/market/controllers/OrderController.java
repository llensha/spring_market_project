package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.beans.Cart;
import ru.geekbrains.spring.market.dto.CartDto;
import ru.geekbrains.spring.market.dto.OrderDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
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
        return orderService.findAll(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public void checkout(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Такой пользователь не найден при оформлении заказа"));
        orderService.createOrderFromCart(user);
    }
}
