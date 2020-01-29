package ua.com.bpgdev.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.security.model.User;
import ua.com.bpgdev.security.service.UserService;
import ua.com.bpgdev.security.util.JwtUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService implements UserAuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public Optional<String> login(String username, String password) {
        User user = userService.getByName(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Password incorrect!");
        }
        return Optional.ofNullable(jwtUtils.generateToken(user));
    }

    @Override
    public Optional<String> register(User user) {
        String passwordHash = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(passwordHash);
        userService.save(user);

        return Optional.ofNullable(jwtUtils.generateToken(user));
    }

    @Override
    public Optional<User> findByName(String userName) {
        return Optional.of(userName)
                .flatMap(userService::getByName);
    }

    @Override
    public void logout(User user) {

    }
}
