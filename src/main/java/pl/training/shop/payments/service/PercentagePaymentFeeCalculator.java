package pl.training.shop.payments.service;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;

@RequiredArgsConstructor
public class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public FastMoney calculateFee(FastMoney paymentValue) {
        return paymentValue.multiply(percentage);
    }

}
