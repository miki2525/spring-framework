package pl.training.payments.application.cards;

import pl.training.payments.domain.cards.Card;
import pl.training.payments.domain.cards.CardNumber;
import pl.training.payments.domain.cards.CardRepository;
import pl.training.payments.domain.cards.CardTransaction;

import java.util.List;

public class TransactionService {

    private final CardRepository cardRepository;

    public TransactionService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardTransaction> getTransactions(CardNumber cardNumber) {
        return cardRepository.getByNumber(cardNumber)
                .map(Card::getTransactions)
                .orElseThrow(CardNotFoundException::new);
    }

}
