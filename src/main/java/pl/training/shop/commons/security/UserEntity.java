package pl.training.shop.commons.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

