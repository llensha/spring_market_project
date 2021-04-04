package ru.geekbrains.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.market.models.Cart;


import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}
