package ru.geekbrains.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.market.models.Cart;


import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {

    private List<CartItemDto> items;
    private int totalSum;

    public CartDto(Cart cart) {
        this.totalSum = cart.getTotalSum();
        this.items = cart.getItems().stream().map(CartItemDto::new).collect(Collectors.toList());
    }
}
