package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class CardFactory {

    public Card create(String number, LocalDate expirationDate, double balance, String currencyCode, List<CardTransaction> transactions) {
        var cardNumber = new CardNumber(number);
        var cardBalance = new Money(BigDecimal.valueOf(balance), Currency.getInstance(currencyCode));
        return new Card(cardNumber, expirationDate, cardBalance, transactions);
    }

}
