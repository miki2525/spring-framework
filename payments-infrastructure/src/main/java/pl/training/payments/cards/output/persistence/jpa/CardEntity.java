package pl.training.payments.cards.output.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "Card")
@Getter
@Setter
public class CardEntity {

    @Id
    @Column(name = "CARD_NUMBER")
    private String number;
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;
    private BigDecimal balance;
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Lob
    private String transactions;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false;
        }
         var that = (CardEntity) other;
        return getNumber() != null && Objects.equals(getNumber(), that.getNumber());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
