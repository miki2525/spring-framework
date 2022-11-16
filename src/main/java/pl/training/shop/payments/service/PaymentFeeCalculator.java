package pl.training.shop.payments.service;

import org.javamoney.moneta.Money;

public interface PaymentFeeCalculator {

    Money calculateFee(Money paymentValue);

}
