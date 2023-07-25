package pl.training.payments.cards.output.persistence.jpa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.payments.domain.cards.Card;
import pl.training.payments.domain.cards.CardFactory;
import pl.training.payments.domain.cards.CardNumber;
import pl.training.payments.domain.cards.CardTransaction;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public abstract class JpaCardRepositoryMapper {

    private static final TypeReference<List<CardTransaction>> transactionsType = new TypeReference<>() {
    };

    private final CardFactory cardFactory = new CardFactory();
    private final ObjectMapper jsonMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

    public String toEntity(CardNumber number) {
        return number.value();
    }

    @Mapping(source = "balance.amount", target = "balance")
    @Mapping(source = "balance.currency.currencyCode", target = "currencyCode")
    public abstract CardEntity toEntity(Card card);

    public Card toDomain(CardEntity cardEntity) {
        return cardFactory.create(cardEntity.getNumber(), cardEntity.getExpirationDate(), cardEntity.getBalance().doubleValue(),
                cardEntity.getCurrencyCode(), fromJson(cardEntity.getTransactions()));
    }

    @SneakyThrows
    protected String toJson(List<CardTransaction> transactions) {
        return jsonMapper.writeValueAsString(transactions);
    }

    @SneakyThrows
    protected List<CardTransaction> fromJson(String json) {
        return jsonMapper.readValue(json, transactionsType);
    }

}
