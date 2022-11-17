package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
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

}
