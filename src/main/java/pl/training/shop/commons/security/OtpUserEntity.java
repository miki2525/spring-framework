package pl.training.shop.commons.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "OtpUser")
@Getter
@Setter
public class OtpUserEntity {

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
        var otherUser = (OtpUserEntity) otherObject;
        return Objects.equals(id, otherUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
