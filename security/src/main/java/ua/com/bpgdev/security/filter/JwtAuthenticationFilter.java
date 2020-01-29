package ua.com.bpgdev.security.filter;

import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import ua.com.bpgdev.security.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String BEARER = "Bearer ";
    private static final String AUTHENTICATION_FAILED = "Authentication FAILED!";
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                      JwtUtils jwtUtils) {
        super(requiresAuthenticationRequestMatcher);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        String user = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String jwt = authorizationHeader.substring(7);
            user = jwtUtils.validateToken(jwt);
        }
        if (!hasText(user)) {
            throw new BadCredentialsException(AUTHENTICATION_FAILED);
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user);
        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    @Override
    @SneakyThrows
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException authException) {

        logger.warn(AUTHENTICATION_FAILED);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(new JSONObject()
                .put("timestamp", LocalDateTime.now())
                .put("status", HttpServletResponse.SC_UNAUTHORIZED)
                .put("error", AUTHENTICATION_FAILED)
                .put("message", AUTHENTICATION_FAILED)
                .toString());
        response.getWriter().flush();
    }
}
