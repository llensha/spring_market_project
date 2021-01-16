package ru.geekbrains.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.market.models.Product;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private int price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

}
