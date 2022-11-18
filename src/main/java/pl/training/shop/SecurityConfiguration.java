package pl.training.shop;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.training.shop.security.CredentialsRepository;
import pl.training.shop.security.CustomAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
           .authorizeRequests()
                .mvcMatchers("/payments/process").hasRole("ADMIN")
           .mvcMatchers("/**").permitAll()
           .and()
           .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.html")
            .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true);
        return http.build();
    }

    @Bean
    public GoogleAuthenticator googleAuthenticator(CredentialsRepository credentialsRepository) {
        var authenticator = new GoogleAuthenticator();
        authenticator.setCredentialRepository(credentialsRepository);
        return authenticator;
    }

}
