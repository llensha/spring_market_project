package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.dto.CartDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Cart;
import ru.geekbrains.spring.market.services.CartService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public UUID createNewCart() {
        Cart cart = cartService.save(new Cart());
        return cart.getId();
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        Cart cart = cartService.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с id = " + uuid));;
        return new CartDto(cart);
    }

    @GetMapping("/{uuid}/add/{product_id}")
    public void addProductToCart(@PathVariable UUID uuid, @PathVariable(name = "product_id") Long productId) {
        cartService.addToCart(uuid, productId);
    }

//    @GetMapping("/inc/{id}")
//    public void incQuantity(@PathVariable Long id) {
//        cart.incQuantity(id);
//    }
//
//    @GetMapping("/dec/{id}")
//    public void decQuantity(@PathVariable Long id) {
//        cart.decQuantity(id);
//    }
//
//    @GetMapping("/delete/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        cart.deleteFromCart(id);
//    }
//
//    @GetMapping("/delete")
//    public void deleteAllProducts() {
//        cart.deleteAll();
//    }

}
