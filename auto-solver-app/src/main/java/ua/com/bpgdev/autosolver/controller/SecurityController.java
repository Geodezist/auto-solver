package ua.com.bpgdev.autosolver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.bpgdev.autosolver.dto.security.JwtSecurityDto;
import ua.com.bpgdev.security.model.AuthenticationRequest;
import ua.com.bpgdev.security.model.AuthenticationResponse;
import ua.com.bpgdev.security.model.User;
import ua.com.bpgdev.security.service.UserAuthenticationService;
import ua.com.bpgdev.security.util.DateUtils;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SecurityController {

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String userName = authenticationRequest.getUsername();
        String jwtToken = userAuthenticationService
                .login(userName, authenticationRequest.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
        return new ResponseEntity<>(new JwtSecurityDto(userName, jwtToken), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = new User(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword(),
                authenticationRequest.getRoles(),
                authenticationRequest.getEmail(),
                DateUtils.addMonths(new Date(), 1));
        userAuthenticationService.register(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(user.toString());
        return ResponseEntity.ok().body(authenticationResponse);
    }
}
