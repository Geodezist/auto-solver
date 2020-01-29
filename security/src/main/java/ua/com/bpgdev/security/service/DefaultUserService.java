package ua.com.bpgdev.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.security.dao.UserDao;
import ua.com.bpgdev.security.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Optional<User> getByName(String userName) {
        return userDao.findByName(userName);
    }

    @Override
    public boolean save(User user) {
        userDao.save(user);
        return true;
    }
}
