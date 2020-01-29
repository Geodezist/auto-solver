package ua.com.bpgdev.security.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.security.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByName(String userName);
}
