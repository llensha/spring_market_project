package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.market.dto.CartItemDto;
import ru.geekbrains.spring.market.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItemDto> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {
        cartService.addProduct(id);
    }

    @GetMapping("/reduce/{id}")
    public void reduceNumberOfProduct(@PathVariable Long id) {
        cartService.reduceNumberOfProduct(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        cartService.deleteProduct(id);
    }

    @GetMapping("/delete")
    public void deleteAllProducts() {
        cartService.deleteAllProducts();
    }

}
