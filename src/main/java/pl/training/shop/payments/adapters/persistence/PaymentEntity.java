package pl.training.shop.payments.adapters.persistence;

import javax.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    @Id
    private String id;
    @Column(name = "amount")
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;

}
