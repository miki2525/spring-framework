package pl.training.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {

    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (jpaUserRepository.getByName("marta").isEmpty()) {
            var user = new UserEntity();
            user.setName("marta");
            user.setPassword(passwordEncoder.encode("123"));
            user.setEmail("marta@training.pl");
            user.setRole("ROLE_ADMIN");
            user.setEnabled(true);
            jpaUserRepository.save(user);
        }
    }

}
