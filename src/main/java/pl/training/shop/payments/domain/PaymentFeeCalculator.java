package pl.training.shop.payments.domain;

import org.javamoney.moneta.FastMoney;

public interface PaymentFeeCalculator {

    FastMoney calculateFee(FastMoney paymentValue);

}
