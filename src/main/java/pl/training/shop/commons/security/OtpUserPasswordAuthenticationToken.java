package pl.training.shop.commons.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class OtpUserPasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;
    private final String password;
    private final String code;

    public OtpUserPasswordAuthenticationToken(String username, String password, String code, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public OtpUserPasswordAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities) {
        this(username, "", "", authorities);
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

}
