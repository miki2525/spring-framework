package pl.training.shop.commons.security;

import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CredentialsRepository implements ICredentialRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public String getSecretKey(String username) {
        return jpaUserRepository.getByName(username)
                .map(OtpUserEntity::getSecret)
                .map(String::valueOf)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void saveUserCredentials(String username, String secret, int code, List<Integer> codes) {
    }

}
