package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.beans.Cart;
import ru.geekbrains.spring.market.dto.ProductDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Order;
import ru.geekbrains.spring.market.models.Product;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.repositories.OrderRepository;
import ru.geekbrains.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAll(String username) {
        return orderRepository.findAllByUserUsername(username);
    }

    public Order createOrderFromCart(User user) {
        Order order = new Order(cart, user);
        order = orderRepository.save(order);
        cart.deleteAll();
        return order;
    }

}
