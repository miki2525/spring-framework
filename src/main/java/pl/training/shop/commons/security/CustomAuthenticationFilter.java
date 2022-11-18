package pl.training.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher("/login.html", "POST");
    private static final String TOKEN = "token";

    private final AuthenticationConfiguration authenticationConfiguration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (REQUEST_MATCHER.matches(request)) {
            var authentication = getAuthentication(request);
            try {
                var authenticationResult = authenticationConfiguration.getAuthenticationManager()
                        .authenticate(authentication);

                var context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authenticationResult);
                SecurityContextHolder.setContext(context);

                response.sendRedirect("index.html");
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(401);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var token = request.getParameter(TOKEN);
        return new CustomAuthentication(username, password, token);
    }

}
