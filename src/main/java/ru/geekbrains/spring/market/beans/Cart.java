//package ru.geekbrains.spring.market.beans;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.WebApplicationContext;
//import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
//import ru.geekbrains.spring.market.models.OrderItem;
//import ru.geekbrains.spring.market.models.Product;
//import ru.geekbrains.spring.market.services.ProductService;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Data
//@RequiredArgsConstructor
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class Cart {
//
//    private final ProductService productService;
//    private List<OrderItem> items;
//    private int totalSum;
//
//    @PostConstruct
//    public void init() {
//        this.items = new ArrayList<>();
//    }
//
//    public void addToCart(Long id) {
//        if (!incQuantity(id)) {
//            Product product = productService.findProductById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт с id = " + id + "при добавлении в корзину"));
//            OrderItem orderItem = new OrderItem(product);
//            items.add(orderItem);
//            recalculate();
//        }
//    }
//
//    public void recalculate() {
//        totalSum = 0;
//        for (OrderItem o : items) {
//            totalSum += o.getSum();
//        }
//    }
//
//    public boolean incQuantity(Long id) {
//        for (OrderItem o : items) {
//            if (o.getId().equals(id)) {
//                o.incrementQuantity();
//                recalculate();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void decQuantity(Long id) {
//        for (OrderItem o : items) {
//            if (o.getId().equals(id)) {
//                if (o.getQuantity() > 1) {
//                    o.decrementQuantity();
//                } else {
//                    items.remove(o);
//                }
//                recalculate();
//                return;
//            }
//        }
//    }
//
//    public void deleteFromCart(Long id) {
//        items.removeIf(oi -> oi.getId().equals(id));
//        recalculate();
//    }
//
//    public void deleteAll() {
//        items.clear();
//        recalculate();
//    }
//
//}
