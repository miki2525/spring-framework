package pl.training.payments.cards.output.persistence;

import org.springframework.stereotype.Repository;
import pl.training.payments.domain.cards.Card;
import pl.training.payments.domain.cards.CardNumber;
import pl.training.payments.domain.cards.CardRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCardRepository implements CardRepository {

    private final Map<CardNumber, Card> cards = new HashMap<>();

    @Override
    public Optional<Card> getByNumber(CardNumber number) {
        return Optional.ofNullable(cards.get(number));
    }

    @Override
    public void save(Card card) {
        cards.put(card.getNumber(), card);
    }

}
