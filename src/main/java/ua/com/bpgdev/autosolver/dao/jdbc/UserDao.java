package ua.com.bpgdev.autosolver.dao.jdbc;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.User;

import java.util.Optional;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByName(String userName);
}
