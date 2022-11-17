package pl.training.shop.commons.security;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecurityUserMapper {

    ShopUserDetails toDomain(UserEntity userEntity);

}
