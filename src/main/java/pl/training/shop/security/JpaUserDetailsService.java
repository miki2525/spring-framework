package pl.training.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;
    private final SecurityUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jpaUserRepository.getByName(username)
                .map(mapper::toDomain)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }

}
