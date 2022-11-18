package pl.training.shop.commons.security;

import org.mapstruct.Mapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SecurityUserMapper {

    default OtpUserDetails toModel(OtpUserEntity otpUserEntity) {
        var user = new OtpUserDetails();
        user.setId(otpUserEntity.getId());
        user.setName(otpUserEntity.getName());
        user.setPassword(otpUserEntity.getPassword());
        user.setSecret(otpUserEntity.getSecret());
        user.setEnabled(otpUserEntity.isEnabled());
        user.setAuthorities(Set.of(new SimpleGrantedAuthority(otpUserEntity.getRole())));
        return user;
    }

}
