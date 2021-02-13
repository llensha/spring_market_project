package ru.geekbrains.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.market.beans.Cart;
import ru.geekbrains.spring.market.models.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private int totalSum;
    private String creationDateTime;
    private String username;
    private String address;
    private List<OrderItemDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.totalSum = order.getTotalSum();
        this.creationDateTime = order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        this.username = order.getUser().getUsername();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
