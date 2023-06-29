package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.POST;

@EnableGlobalAuthentication
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http//.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(config -> config
                .requestMatchers("/login.html").permitAll()
                .requestMatchers(POST, "/payments/process").hasRole("ADMIN")
                .requestMatchers("/**").authenticated())
            .formLogin(config -> config.loginPage("/login.html"))
            .logout(config -> config
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                 .logoutSuccessUrl("/login.html")
                 .invalidateHttpSession(true))
            .headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        var manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select username, password, enabled from app_users where username = ?");
        return manager;
    }

}
