package ua.com.bpgdev.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.security.model.User;
import ua.com.bpgdev.security.model.JwtUserDetails;
import ua.com.bpgdev.security.service.UserAuthenticationService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserAuthenticationService userAuthenticationService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //Nothing to implement
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final Object token = authentication.getCredentials();
        User user = Optional.ofNullable(token)
                .map(String::valueOf)
                .flatMap(userAuthenticationService::findByName)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));

        return new JwtUserDetails(user);
    }
}
