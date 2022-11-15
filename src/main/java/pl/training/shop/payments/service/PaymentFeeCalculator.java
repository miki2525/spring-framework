package pl.training.shop.payments.service;

import org.javamoney.moneta.FastMoney;

public interface PaymentFeeCalculator {

    FastMoney calculateFee(FastMoney paymentValue);

}
