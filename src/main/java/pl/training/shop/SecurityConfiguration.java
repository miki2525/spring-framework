package pl.training.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.training.shop.commons.security.DynamicAccessDecisionVoter;

import javax.sql.DataSource;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    /*
    AuthenticationManager authenticationManager; // Abstrakcja odpowiadająca za proces uwierzytelnienia
        ProviderManager providerManager; // Implementuje AuthenticationManager, deleguje uwierzytelnienie do jednego ze skonfigurowanych AuthenticationProvider
            AuthenticationProvider authenticationProvider; // Fizycznie przeprowadza uwierzytelnienie np. DaoAuthenticationProvider, LdapAuthenticationProvider, OpenIDAuthenticationProvider
                DaoAuthenticationProvider daoAuthenticationProvider; // Ładuje dane za pomocą usługi UserDetailsService i porównuje z danymi przekazanymi w Authentication


    Authentication authentication; // Reprezentuje dane niezbędne do uwierzytelnienia, ale także status po uwierzytelnieniu mplementowane np. przez UsernamePasswordAuthenticationToken
    UserDetails userDetails; // Reprezentuje użytkownika / konto
    GrantedAuthority grantedAuthority; // Reprezentuje uprawnienia / role
    SecurityContext securityContext; // Przechowuje Authentication zalogowanego użytkownika
    SecurityContextHolder securityContextHolder; // Przechowuje SecurityContext dla użytkownika (domyślna strategia ThreadLocal)
    PasswordEncoder passwordEncoder; // Abstrakcja obiektu umożliwiającego hashowanie i porównywanie haseł
        BCryptPasswordEncoder bCryptPasswordEncoder;

    // Deprecated
    AccessDecisionManager decisionManager; // Abstrakcja odpowiadająca za autoryzację dostępu np. AffirmativeBased, ConsensusBased, UnanimousBased
        // Podejmuje decyzję na podstawie głosowania AccessDecisionVoter accessDecisionVoter

    AuthorizationManager authorizationManager;
     */

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService jpaUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?");*/

        /*auth.inMemoryAuthentication()
                .withUser("marek")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN");*/

        // auth.ldapAuthentication()

        auth.userDetailsService(jpaUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.csrf().disable()
                .authorizeRequests()
                    .mvcMatchers("/login.html").permitAll()
                    .mvcMatchers(POST, "/payments/process").hasRole("ADMIN")
                    .mvcMatchers("/**").authenticated().accessDecisionManager(accessDecisionManager())
                .and()
                    //.httpBasic()
                    .formLogin()
                    .loginPage("/login.html")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                    .logoutSuccessUrl("/login.html")
                    .invalidateHttpSession(true)
                .and()
                    .headers()
                    .frameOptions()
                    .disable();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AffirmativeBased(List.of(new DynamicAccessDecisionVoter()));
    }

}
