package pl.training.shop.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Map;

public class DynamicAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    private final Map<String, String> rules = Map.of(
            "/payments/process", "ROLE_ADMIN",
            "/api/users/me", "ROLE_ADMIN"
    );

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> attributes) {
        var authorities = authentication.getAuthorities();
        var url = filterInvocation.getRequestUrl();
        if (!rules.containsKey(url)) {
            return ACCESS_GRANTED;
        }
        var requiredRole = rules.getOrDefault(url, "");
        return authorities.stream()
                .map(GrantedAuthority::toString)
                .toList()
                .contains(requiredRole) ? ACCESS_GRANTED : ACCESS_DENIED;
    }

}
