package ru.geekbrains.spring.market.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.OrderItem;
import ru.geekbrains.spring.market.models.Product;
import ru.geekbrains.spring.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Cart {

    private final ProductService productService;
    private List<OrderItem> items;
    private int totalSumma;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт с id = " + id + "при добавлении в корзину"));
        OrderItem orderItem = new OrderItem(product);
        items.add(orderItem);
        recalculate();
    }

    public void recalculate() {
        totalSumma = 0;
        for (OrderItem o : items) {
            totalSumma += o.getSumma();
        }
    }

    public void incQuantity(Long id) {
        for (OrderItem o : items) {
            if (o.getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
    }
    public void decQuantity(Long id) {
        for (OrderItem o : items) {
            if (o.getId().equals(id)) {
                if (o.getQuantity() > 1) {
                    o.decrementQuantity();
                } else {
                    items.remove(o);
                }
                recalculate();
                return;
            }
        }
    }

    public void deleteFromCart(Long id) {
        for (OrderItem o : items) {
            if (o.getId().equals(id)) {
                items.remove(o);
                recalculate();
                return;
            }
        }
    }

    public void deleteAll() {
        items.clear();
        recalculate();
    }

}
