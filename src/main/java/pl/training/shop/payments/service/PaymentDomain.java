package pl.training.shop.payments.service;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.Money;

import java.time.Instant;

@Builder
@Value
public class PaymentDomain {

    String id;
    Money value;
    Instant timestamp;
    PaymentStatusDomain status;

}
