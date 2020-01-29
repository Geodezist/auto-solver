package ua.com.bpgdev.security.service;

import ua.com.bpgdev.security.model.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<String> login(String username, String password);
    Optional<String> register(User user);
    Optional<User> findByName(String token);
    void logout(User user);
}
