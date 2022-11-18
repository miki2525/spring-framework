package pl.training.shop.commons.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomAuthentication extends AbstractAuthenticationToken {

    private String username;
    private String password;
    private String token;

    public CustomAuthentication(String username, String password, String token) {
        super(List.of());
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public CustomAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

}
