package pl.training.payments.application.input.queries;

import pl.training.payments.application.cards.TransactionService;
import pl.training.payments.domain.cards.CardTransaction;

import java.util.List;

public class GetCardTransactionsQueryHandler {

    private final TransactionService transactionService;

    public GetCardTransactionsQueryHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public List<CardTransaction> handle(GetCardTransactionsQuery getCardTransactionsQuery) {
        return transactionService.getTransactions(getCardTransactionsQuery.cardNumber());
    }

}
