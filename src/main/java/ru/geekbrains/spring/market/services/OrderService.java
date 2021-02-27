package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.beans.Cart;
import ru.geekbrains.spring.market.models.Order;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAll(String username) {
        return orderRepository.findAllByUserUsername(username);
    }

    public Order createOrderFromCart(User user, String address) {
        Order order = new Order(cart, user, address);
        order = orderRepository.save(order);
        cart.deleteAll();
        return order;
    }

}
