package ua.com.bpgdev.security.service;

import ua.com.bpgdev.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getByName(String userName);
    boolean save(User user);
}
