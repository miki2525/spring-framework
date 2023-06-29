package pl.training.shop.commons.security;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String password;
    private boolean enabled;
    private String email;
    private String role;

}

