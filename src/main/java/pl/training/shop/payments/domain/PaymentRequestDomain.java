package pl.training.shop.payments.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
public class PaymentRequestDomain {

    Long id;
    FastMoney value;

}
