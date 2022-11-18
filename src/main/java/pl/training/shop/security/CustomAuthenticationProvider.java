package pl.training.shop.security;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final GoogleAuthenticator authenticator;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var customAuthentication = (CustomAuthentication) authentication;
        var user = (ShopUserDetails) userDetailsService.loadUserByUsername(customAuthentication.getUsername());
        if (!passwordEncoder.matches(customAuthentication.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid user or password");
        }
        if (!authenticator.authorizeUser(user.getName(), Integer.parseInt(customAuthentication.getToken()))) {
            throw new BadCredentialsException("Invalid user or password");
        }
        var result = UsernamePasswordAuthenticationToken.authenticated(customAuthentication.getUsername(), "", user.getAuthorities());
        result.setDetails(user);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.isAssignableFrom(authentication);
    }

}
