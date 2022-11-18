package pl.training.shop.payments.adapters.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    @Id
    private String id;
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;

}
