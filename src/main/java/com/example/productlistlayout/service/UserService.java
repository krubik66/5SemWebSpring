package com.example.productlistlayout.service;

import com.example.productlistlayout.entity.Role;
import com.example.productlistlayout.entity.User;
import com.example.productlistlayout.exception.NonUniqueLoginException;
import com.example.productlistlayout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserByLogin(final String login) {
        return userRepository.findByLogin(login);
    }
    public void registerUser(final User user) throws NonUniqueLoginException {
        if (doesLoginExist(user.getLogin()))
        {
            throw new NonUniqueLoginException("User with this login already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }

    public boolean doesLoginExist(String login) {
        return getUserByLogin(login).isPresent();
    }
}