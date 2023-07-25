package pl.training.payments.domain.cards;

import java.util.Optional;

public interface CardRepository {

    Optional<Card> getByNumber(CardNumber number);

    void save(Card card);

}
