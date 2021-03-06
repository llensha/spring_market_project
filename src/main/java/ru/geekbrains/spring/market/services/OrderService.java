package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.beans.Cart;
import ru.geekbrains.spring.market.dto.OrderDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Order;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.repositories.OrderRepository;

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

    public Optional<OrderDto> findOrderById(Long id) {
        return orderRepository.findById(id).map(OrderDto::new);
    }

    public OrderDto createOrderFromCart(User user, String address) {
        Order order = new Order(cart, user, address);
        order = orderRepository.save(order);
        cart.deleteAll();
        return new OrderDto(order);
    }

}
