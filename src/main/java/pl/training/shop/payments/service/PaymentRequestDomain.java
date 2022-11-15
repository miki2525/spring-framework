package pl.training.shop.payments.service;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
public class PaymentRequestDomain {

    Long id;
    FastMoney value;

}
