package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Cart;
import ru.geekbrains.spring.market.models.CartItem;
import ru.geekbrains.spring.market.models.Product;
import ru.geekbrains.spring.market.repositories.CartRepository;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void addToCart(UUID cartId, Long productId) {
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить товар с id = " + productId + " в корзину. Такой товар не существует"));
        Cart cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Не нвйдена корзина с id = " + cartId));;
        cart.add(new CartItem(product));
    }
}
