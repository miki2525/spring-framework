package pl.training.shop.security;

import org.mapstruct.Mapper;
import pl.training.shop.users.adapters.persistence.UserEntity;

@Mapper(componentModel = "spring")
public interface SecurityUserMapper {

    ShopUserDetails toDomain(UserEntity userEntity);

}
