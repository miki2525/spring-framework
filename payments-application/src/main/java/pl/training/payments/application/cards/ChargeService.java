package pl.training.payments.application.cards;

import pl.training.payments.application.output.events.CardApplicationEventsPublisher;
import pl.training.payments.application.output.events.CardChargedApplicationEvent;
import pl.training.payments.application.output.time.TimeProvider;
import pl.training.payments.domain.cards.*;
import pl.training.payments.domain.money.Money;

import static pl.training.payments.domain.cards.CardTransactionType.FEE;
import static pl.training.payments.domain.cards.CardTransactionType.PAYMENT;

public class ChargeService {

    private final CardFeesService cardFeesService;
    private final CardRepository cardRepository;
    private final CardApplicationEventsPublisher cardApplicationEventsPublisher;
    private final TimeProvider timeProvider;

    public ChargeService(CardFeesService cardFeesService, CardRepository cardRepository, CardApplicationEventsPublisher cardApplicationEventsPublisher, TimeProvider timeProvider) {
        this.cardFeesService = cardFeesService;
        this.cardRepository = cardRepository;
        this.cardApplicationEventsPublisher = cardApplicationEventsPublisher;
        this.timeProvider = timeProvider;
    }

    public void chargeCard(CardNumber cardNumber, Money chargeValue) {
        processCardOperation(cardNumber, card -> {
            card.addEventsListener(this::publishApplicationEvent);
            return new CardTransaction(timeProvider.getTimestamp(), chargeValue, PAYMENT);
        });
    }

    private void publishApplicationEvent(CardChargedEvent cardChargedEvent) {
        var applicationEvent = new CardChargedApplicationEvent(cardChargedEvent.toString());
        cardApplicationEventsPublisher.publish(applicationEvent);
    }

    public void chargeCardFees(CardNumber cardNumber) {
        processCardOperation(cardNumber, card -> {
            var fees = cardFeesService.calculateFees(card.getTransactions());
            return new CardTransaction(timeProvider.getTimestamp(), fees, FEE);
        });
    }

    private void processCardOperation(CardNumber cardNumber, CardOperation operation) {
        var card = cardRepository.getByNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);
        var transaction = operation.execute(card);
        card.addTransaction(transaction);
        cardRepository.save(card);
    }

}
