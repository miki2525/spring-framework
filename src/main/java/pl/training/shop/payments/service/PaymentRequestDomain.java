package pl.training.shop.payments.service;

import lombok.Value;
import org.javamoney.moneta.Money;

@Value
public class PaymentRequestDomain {

    Long id;
    Money value;

}
