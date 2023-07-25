package pl.training.payments.application.input.commands;

import pl.training.payments.application.cards.ChargeService;

public class ChargeCardFeesCommandHandler {

    private final ChargeService chargeService;

    public ChargeCardFeesCommandHandler(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    public void handle(ChargeCardFeesCommand chargeCardFeesCommand) {
        chargeService.chargeCardFees(chargeCardFeesCommand.cardNumber());
    }

}
