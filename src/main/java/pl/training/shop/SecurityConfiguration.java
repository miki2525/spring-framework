package pl.training.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.POST;

// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*

    // Aktywacja uwierzytelniania odbywa się przez zestaw filtrów web, albo aspekty nakładane na beany

    AuthenticationManager authenticationManager; // Abstrakcja odpowiadająca za proces uwierzytelnienia
        ProviderManager providerManager; // Implementuje AuthenticationManager, deleguje uwierzytelnienie do jednego ze skonfigurowanych AuthenticationProvider
            AuthenticationProvider authenticationProvider; // Fizycznie przeprowadza uwierzytelnienie np. DaoAuthenticationProvider, LdapAuthenticationProvide, OpenIDAuthenticationProvider
                DaoAuthenticationProvider daoAuthenticationProvider; // Ładuje dane za pomocą usługi UserDetailsService i porównuje z danymi przekazanymi w Authentication

    Authentication authentication; // Reprezentuje dane niezbędne do uwierzytelnienia, ale także status po uwierzytelnieniu
                                   // Implementowane np. przez UsernamePasswordAuthenticationToken

    UserDetails userDetails; // Reprezentuje użytkownika / konto

    GrantedAuthority grantedAuthority; // Reprezentuje uprawnienia / role

    SecurityContext securityContext; // Przechowuje Authentication zalogowanego użytkownika

    SecurityContextHolder securityContextHolder; // Przechowuje SecurityContext dla użytkownika (domyślna strategia ThreadLocal)

    PasswordEncoder passwordEncoder; // Abstrakcja obiektu umożliwiającego hashowanie i porównywanie haseł
        BCryptPasswordEncoder bCryptPasswordEncoder;

    */

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
                .passwordEncoder(passwordEncoder());*/
        var password = passwordEncoder().encode("123");
        auth.inMemoryAuthentication()
                .withUser("marek").password(password).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/login.html").permitAll()
                    .mvcMatchers(POST, "/payments/process").hasRole("ADMIN")
                    .mvcMatchers("/**").authenticated()
                .and()
                //.httpBasic()
                .formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/login.html")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                    .logoutSuccessUrl("/login.html")
                    .invalidateHttpSession(true);
    }

}
