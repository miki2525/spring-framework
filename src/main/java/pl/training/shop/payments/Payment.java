package pl.training.shop.payments;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.javamoney.moneta.Money;

import java.time.Instant;

@Entity
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    private Money value;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
