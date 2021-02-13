package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.dto.ProductDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.spring.market.services.ProductService;
import ru.geekbrains.spring.market.services.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveNewUser(@RequestBody User newUser) {
        newUser.setId(null);
        return userService.save(newUser);
    }

}
