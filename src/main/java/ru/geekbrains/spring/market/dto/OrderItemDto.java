package ru.geekbrains.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.market.models.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private int price;
    private int summa;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.summa = orderItem.getSumma();
    }

}
