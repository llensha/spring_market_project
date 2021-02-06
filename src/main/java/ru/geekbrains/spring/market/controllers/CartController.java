package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.market.beans.Cart;
import ru.geekbrains.spring.market.dto.CartDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/inc/{id}")
    public void incQuantity(@PathVariable Long id) {
        cart.incQuantity(id);
    }

    @GetMapping("/dec/{id}")
    public void decQuantity(@PathVariable Long id) {
        cart.decQuantity(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        cart.deleteFromCart(id);
    }

    @GetMapping("/delete")
    public void deleteAllProducts() {
        cart.deleteAll();
    }
}
