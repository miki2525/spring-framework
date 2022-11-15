package pl.training.shop.payments.service;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Builder
@Value
public class PaymentDomain {

    String id;
    FastMoney value;
    Instant timestamp;
    PaymentStatusDomain status;

}
