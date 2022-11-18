package pl.training.shop.users.adapters.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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
    private String role;
    private char[] secret;

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        var account = (UserEntity) otherObject;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
