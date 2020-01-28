package ua.com.bpgdev.autosolver.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationResponse {
    private final String jwtToken;
}
