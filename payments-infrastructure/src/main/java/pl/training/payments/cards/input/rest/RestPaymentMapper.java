package pl.training.payments.cards.input.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.payments.application.input.commands.ChargeCardCommand;
import pl.training.payments.application.input.commands.ChargeCardFeesCommand;
import pl.training.payments.application.input.queries.GetCardTransactionsQuery;
import pl.training.payments.domain.cards.CardNumber;
import pl.training.payments.domain.cards.CardTransaction;
import pl.training.payments.domain.money.Money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = {CardNumber.class, Money.class, BigDecimal.class, Currency.class})
public interface RestPaymentMapper {

    @Mapping(target = "cardNumber", expression = "java(new CardNumber(chargeRequestDto.getCardNumber()))")
    @Mapping(target = "chargeValue", expression = "java(new Money(BigDecimal.valueOf(chargeRequestDto.getAmount()), Currency.getInstance(chargeRequestDto.getCurrencyCode())))")
    ChargeCardCommand toDomain(ChargeRequestDto chargeRequestDto);

    @Mapping(target = "cardNumber", expression = "java(new CardNumber(chargeFeesDto.getCardNumber()))")
    ChargeCardFeesCommand toDomain(ChargeFeesDto chargeFeesDto);

    @Mapping(target = "timestamp", expression = "java(transaction.timestamp().toString())")
    @Mapping(target = "amount", expression = "java(transaction.value().amount().toString() + ' ' + transaction.value().currency().getCurrencyCode())")
    CardTransactionDto toDto(CardTransaction transaction);

    @IterableMapping(elementTargetType = CardTransactionDto.class)
    List<CardTransactionDto> toDtos(List<CardTransaction> transactions);

    default GetCardTransactionsQuery toDomain(String cardNumber) {
        return new GetCardTransactionsQuery(new CardNumber(cardNumber));
    }

}
