package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import java.util.List;

public class CardFeesService {

    private final TransactionFeePolicy transactionFeePolicy;

    public CardFeesService(TransactionFeePolicy transactionFeePolicy) {
        this.transactionFeePolicy = transactionFeePolicy;
    }

    public Money calculateFees(List<CardTransaction> transactions) {
        return transactionFeePolicy.execute(transactions);
    }

}
