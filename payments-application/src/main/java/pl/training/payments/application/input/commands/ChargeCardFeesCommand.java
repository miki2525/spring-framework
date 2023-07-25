package pl.training.payments.application.input.commands;

import pl.training.payments.domain.cards.CardNumber;

public record ChargeCardFeesCommand(CardNumber cardNumber) {
}
