package ua.com.bpgdev.autosolver.service;

import ua.com.bpgdev.autosolver.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getByName(String userName);
    boolean save(User user);
}
