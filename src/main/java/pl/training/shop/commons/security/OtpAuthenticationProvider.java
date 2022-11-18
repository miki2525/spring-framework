package pl.training.shop.commons.security;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final GoogleAuthenticator authenticator;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var otpAuthentication = (OtpUserPasswordAuthenticationToken) authentication;
        var otpUser = (OtpUserDetails) userDetailsService.loadUserByUsername(otpAuthentication.getUsername());
        if (!passwordIsValid(otpAuthentication.getPassword(), otpUser.getPassword()) ||
            !codeIsValid(otpUser.getName(), otpAuthentication.getCode())) {
            throw new BadCredentialsException("Invalid user or password");
        }
        return new OtpUserPasswordAuthenticationToken(otpUser.getName(), otpUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpUserPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean passwordIsValid(CharSequence rawPassword, String encodedPassword) {
       return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private boolean codeIsValid(String username, String code) {
        return authenticator.authorizeUser(username, parseInt(code));
    }

}
