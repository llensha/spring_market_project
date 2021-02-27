package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.exceptions_handling.MarketError;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.services.UserService;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveNewUser(@RequestBody User newUser) {
        if (userService.isUserExists(newUser.getUsername(), newUser.getEmail())) {
            log.error("Пользователь с таким логином или email уже существует");
            return new ResponseEntity<>
                    (new MarketError(HttpStatus.CONFLICT.value(), "Пользователь с таким логином или email уже существует"), HttpStatus.CONFLICT);
        } else {
            newUser.setId(null);
            userService.save(newUser);
            return ResponseEntity.ok(HttpStatus.CREATED);
        }
    }

}
