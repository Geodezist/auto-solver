package ua.com.bpgdev.autosolver.dto.security;

import lombok.Data;

@Data
public class JwtSecurityDto {
    private final String userName;
    private String roles;
    private final String token;
}
