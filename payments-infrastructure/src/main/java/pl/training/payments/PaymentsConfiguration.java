package pl.training.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.payments.application.cards.TransactionService;
import pl.training.payments.application.input.commands.ChargeCardCommandHandler;
import pl.training.payments.application.input.commands.ChargeCardFeesCommandHandler;
import pl.training.payments.application.input.queries.GetCardTransactionsQueryHandler;
import pl.training.payments.application.output.events.CardApplicationEventsPublisher;
import pl.training.payments.application.output.time.TimeProvider;
import pl.training.payments.application.cards.ChargeService;
import pl.training.payments.domain.cards.*;
import pl.training.payments.domain.money.Money;

import java.util.Currency;

import static java.math.BigDecimal.ONE;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public CardFeesService cardFeesService() {
        var feePolicy = new PerTransactionFeePolicy(new Money(ONE, Currency.getInstance("PLN")));
        return new CardFeesService(feePolicy);
    }

    @Bean
    public ChargeService paymentService(CardRepository cardRepository, CardApplicationEventsPublisher cardApplicationEventsPublisher,
                                        CardFeesService cardFeesService, TimeProvider timeProvider) {
        return new ChargeService(cardFeesService, cardRepository, cardApplicationEventsPublisher, timeProvider);
    }

    @Bean
    public TransactionService transactionService(CardRepository cardRepository) {
        return new TransactionService(cardRepository);
    }

    @Bean
    public ChargeCardCommandHandler chargeCardCommandHandler(ChargeService chargeService) {
        return new ChargeCardCommandHandler(chargeService);
    }

    @Bean
    public ChargeCardFeesCommandHandler chargeCardFeesCommandHandler(ChargeService chargeService) {
        return new ChargeCardFeesCommandHandler(chargeService);
    }

    @Bean
    public GetCardTransactionsQueryHandler getTransactionsQueryHandler(TransactionService transactionService) {
        return new GetCardTransactionsQueryHandler(transactionService);
    }

    @Bean
    public CardFactory cardFactory() {
        return new CardFactory();
    }

}
