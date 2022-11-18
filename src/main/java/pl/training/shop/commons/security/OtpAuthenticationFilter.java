package pl.training.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher("/login.html", "POST");
    private static final String REDIRECT_URL = "/";
    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String CODE_PARAMETER = "code";

    private final AuthenticationConfiguration authenticationConfiguration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (REQUEST_MATCHER.matches(request)) {
            var authentication = getAuthentication(request);
            try {
                var authenticationResult = authenticationConfiguration.getAuthenticationManager()
                        .authenticate(authentication);
                createSecurityContext(authenticationResult);
                response.sendRedirect(REDIRECT_URL);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HTTP_UNAUTHORIZED);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        var username = request.getParameter(USERNAME_PARAMETER);
        var password = request.getParameter(PASSWORD_PARAMETER);
        var code = request.getParameter(CODE_PARAMETER);
        return new OtpUserPasswordAuthenticationToken(username, password, code, emptyList());
    }

    private void createSecurityContext(Authentication authentication) {
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

}
