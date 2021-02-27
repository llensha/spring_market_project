package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.models.Role;
import ru.geekbrains.spring.market.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<List<Role>> findByRoleName(String roleName) {
        return roleRepository.findByNameIsLike(roleName);
    }

}