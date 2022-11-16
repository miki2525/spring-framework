package pl.training.shop.payments.domain;

import org.javamoney.moneta.Money;

class PaymentFixtures {

    static final String DEFAULT_CURRENCY_CODE = "PLN";

    static Money moneyOf(double value) {
        return Money.of(value, DEFAULT_CURRENCY_CODE);
    }

}
