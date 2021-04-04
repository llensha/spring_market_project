package ru.geekbrains.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.market.models.CartItem;


@NoArgsConstructor
@Data
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int price;
    private int sum;

    public CartItemDto(CartItem cartItem) {
        this.productId = cartItem.getProduct().getId();
        this.productTitle = cartItem.getProduct().getTitle();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.sum = cartItem.getSum();
    }
}
