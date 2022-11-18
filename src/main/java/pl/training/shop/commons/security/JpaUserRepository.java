package pl.training.shop.commons.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<OtpUserEntity, Long> {

    Optional<OtpUserEntity> getByName(String name);

}
