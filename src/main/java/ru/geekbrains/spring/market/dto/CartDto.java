package ru.geekbrains.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.market.beans.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {

    private List<OrderItemDto> items;
    private int totalSumma;

    public CartDto(Cart cart) {
        this.totalSumma = cart.getTotalSumma();
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
