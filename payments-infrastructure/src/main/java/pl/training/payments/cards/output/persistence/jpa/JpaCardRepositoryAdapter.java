package pl.training.payments.cards.output.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.training.payments.domain.cards.Card;
import pl.training.payments.domain.cards.CardNumber;
import pl.training.payments.domain.cards.CardRepository;

import java.util.Optional;

@Primary
@Transactional
@Repository
@RequiredArgsConstructor
public class JpaCardRepositoryAdapter implements CardRepository {

    private final JpaCardRepository cardRepository;
    private final JpaCardRepositoryMapper mapper;

    @Override
    public Optional<Card> getByNumber(CardNumber number) {
        var cardNumber = mapper.toEntity(number);
        return cardRepository.findById(cardNumber)
                .map(mapper::toDomain);
    }

    @Override
    public void save(Card card) {
        var cardEntity = mapper.toEntity(card);
        cardRepository.save(cardEntity);
    }

}
