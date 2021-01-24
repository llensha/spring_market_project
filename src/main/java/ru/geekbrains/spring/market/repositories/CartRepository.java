package ru.geekbrains.spring.market.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.market.dto.CartItemDto;
import ru.geekbrains.spring.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CartRepository {

    private Map<Product, Integer> cart;

    @PostConstruct
    public void init() {
        this.cart = new HashMap<>();
    }

    public List<CartItemDto> getCart() {
        return cart.entrySet().stream().map(entry -> new CartItemDto(
                entry.getKey().getId(),
                entry.getKey().getTitle(),
                entry.getKey().getPrice(),
                entry.getValue()
        )).collect(Collectors.toUnmodifiableList());
    }

    public void addToCart(Product product) {
        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    public void reduceNumber(Product product) {
        if (cart.get(product) > 1) {
            cart.replace(product, cart.get(product) - 1);
        } else {
            cart.remove(product);
        }
    }

    public void deleteFromCart(Product product) {
        cart.remove(product);
    }

    public void deleteAll() {
        cart.clear();
    }
}
