package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.dto.CartItemDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Product;
import ru.geekbrains.spring.market.repositories.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public List<CartItemDto> findAll() {
        return cartRepository.getCart();
    }

    public void addProduct(Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
        cartRepository.addToCart(product);
    }

    public void reduceNumberOfProduct(Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
        cartRepository.reduceNumber(product);
    }

    public void deleteProduct(Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
        cartRepository.deleteFromCart(product);
    }

    public void deleteAllProducts() {
        cartRepository.deleteAll();
    }

}
