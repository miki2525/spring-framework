package pl.training.shop.security;

import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.training.shop.users.adapters.persistence.UserEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log
public class CredentialsRepository implements ICredentialRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public String getSecretKey(String username) {
        return jpaUserRepository.getByName(username)
                .map(UserEntity::getSecret)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void saveUserCredentials(String username, String secret, int code, List<Integer> codes) {
    }

}
