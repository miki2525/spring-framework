package pl.training.shop.security;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.shop.users.adapters.persistence.UserEntity;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getByName(String name);

}
