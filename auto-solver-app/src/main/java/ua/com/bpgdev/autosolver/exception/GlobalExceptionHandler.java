package ua.com.bpgdev.autosolver.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.com.bpgdev.autosolver.exception.model.ErrorResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class, ExpiredJwtException.class})
    public ResponseEntity<ErrorResponse> authenticationExceptionHandler(AuthenticationException e){
        log.warn("Authentication exception: {}", e.getMessage());
        return getResponseEntity(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> defaultExceptionHandler(Exception e) {
        log.warn("Unexpected error occurred with message: {}", e.getMessage());
        return getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ErrorResponse> getResponseEntity(HttpStatus httpStatus, String errorMessage) {
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(errorMessage));
    }
}
