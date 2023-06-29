package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import pl.training.shop.commons.security.KeycloakGrantedAuthorityConverter;
import pl.training.shop.commons.security.KeycloakLogoutHandler;

import static org.springframework.http.HttpMethod.POST;

@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RestTemplate restTemplate) throws Exception {
        http.authorizeHttpRequests(config -> config
                .requestMatchers("/login.html").permitAll()
                .requestMatchers(POST, "/payments/process").hasRole("ADMIN")
                .requestMatchers("/**").authenticated())
             .oauth2Login(config -> {})
             .oauth2ResourceServer(config -> config
                .jwt(this::jwtConfig))
             .logout(config -> config
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                .addLogoutHandler(new KeycloakLogoutHandler(restTemplate))
                .logoutSuccessUrl("/index.html"))
             .headers(config -> config
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    private void jwtConfig(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer jwtConfigurer) {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakGrantedAuthorityConverter());
        jwtConfigurer.jwtAuthenticationConverter(converter);
    }

}
