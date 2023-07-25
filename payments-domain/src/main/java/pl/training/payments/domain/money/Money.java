package pl.training.payments.domain.money;

import java.math.BigDecimal;
import java.util.Currency;

import static java.math.BigDecimal.ZERO;

public record Money(BigDecimal amount, Currency currency) {

    public Money {
        if (amount.compareTo(ZERO) < 1) {
            throw new InvalidAmountException();
        }
    }

    public Money add(Money money) {
        checkCurrencyCompatibility(money);
        var resultAmount = amount.add(money.amount);
        return new Money(resultAmount, currency);
    }

    public Money subtract(Money money) {
        checkCurrencyCompatibility(money);
        var resultAmount = amount.subtract(money.amount);
        return new Money(resultAmount, currency);
    }

    public boolean isGreaterOrEqual(Money money) {
        checkCurrencyCompatibility(money);
        return amount.compareTo(money.amount) > -1;
    }

    private void checkCurrencyCompatibility(Money money) {
        if (!currency.equals(money.currency)) {
            throw new CurrencyMismatchException();
        }
    }

    public Money multiplyBy(BigDecimal value) {
        var resultAmount = amount.multiply(value);
        return new Money(resultAmount, currency);
    }

}
