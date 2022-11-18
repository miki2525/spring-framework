package pl.training.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final JpaUserRepository jpaUserRepository;

    public void addUser(String name, String password, String email) {
        if (jpaUserRepository.getByName(name).isPresent()) {
            throw new IllegalStateException();
        }
        var user = new UserEntity();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);
        user.setSecret(tokenManager.generateSecret());
        user.setTwoFactorAuthentication(true);
        jpaUserRepository.save(user);
    }

    public Token createToken(String name) {
        return jpaUserRepository.getByName(name)
                .map(user -> new Token(tokenManager.createQr(user.getSecret()), user.getSecret()))
                .orElseThrow(IllegalStateException::new);
    }

}
