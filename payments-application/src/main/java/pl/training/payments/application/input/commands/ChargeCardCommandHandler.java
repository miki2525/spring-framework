package pl.training.payments.application.input.commands;

import pl.training.payments.application.cards.ChargeService;

public class ChargeCardCommandHandler {

    private final ChargeService chargeService;

    public ChargeCardCommandHandler(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    public void handle(ChargeCardCommand chargeCardCommand) {
        chargeService.chargeCard(chargeCardCommand.cardNumber(), chargeCardCommand.chargeValue());
    }

}
