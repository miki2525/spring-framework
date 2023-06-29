package pl.training.shop;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

    /*
    AuthenticationManager authenticationManager; // Abstrakcja odpowiadająca za proces uwierzytelnienia
        ProviderManager providerManager; // Implementuje AuthenticationManager, deleguje uwierzytelnienie do jednego ze skonfigurowanych AuthenticationProvider
            AuthenticationProvider authenticationProvider; // Fizycznie przeprowadza uwierzytelnienie np. DaoAuthenticationProvider, LdapAuthenticationProvide, OpenIDAuthenticationProvider
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

}
