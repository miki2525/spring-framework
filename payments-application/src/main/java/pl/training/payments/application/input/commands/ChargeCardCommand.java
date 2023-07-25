package pl.training.payments.application.input.commands;

import pl.training.payments.domain.cards.CardNumber;
import pl.training.payments.domain.money.Money;

public record ChargeCardCommand(CardNumber cardNumber, Money chargeValue) {
}
