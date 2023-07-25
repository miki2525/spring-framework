package pl.training.payments.application.input.queries;

import pl.training.payments.domain.cards.CardNumber;

public record GetCardTransactionsQuery(CardNumber cardNumber) {
}
