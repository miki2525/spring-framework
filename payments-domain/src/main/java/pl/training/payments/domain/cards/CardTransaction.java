package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public record CardTransaction(ZonedDateTime timestamp, Money value, CardTransactionType type) {

    public LocalDate getDate() {
        return timestamp.toLocalDate();
    }

}
