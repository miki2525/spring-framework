package pl.training.payments.application.cards;

import pl.training.payments.domain.cards.Card;
import pl.training.payments.domain.cards.CardTransaction;

public interface CardOperation {

    CardTransaction execute(Card card);

}
