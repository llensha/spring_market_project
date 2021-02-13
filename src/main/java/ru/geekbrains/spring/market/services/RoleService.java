package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.market.dto.ProductDto;
import ru.geekbrains.spring.market.models.Role;
import ru.geekbrains.spring.market.models.User;
import ru.geekbrains.spring.market.repositories.RoleRepository;
import ru.geekbrains.spring.market.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<List<Role>> findByRoleName(String roleName) {
        return roleRepository.findByNameIsLike(roleName);
    }

}