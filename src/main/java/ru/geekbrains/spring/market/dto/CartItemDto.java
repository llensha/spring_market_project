package ru.geekbrains.spring.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDto {

    private Long id;
    private String title;
    private int price;
    private int number;

}
