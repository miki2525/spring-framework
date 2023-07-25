package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import java.math.BigDecimal;
import java.util.List;

public class PerTransactionFeePolicy implements TransactionFeePolicy {

    private final Money singleTransactionFee;

    public PerTransactionFeePolicy(Money singleTransactionFee) {
        this.singleTransactionFee = singleTransactionFee;
    }

    @Override
    public Money execute(List<CardTransaction> transactions) {
        var multiplier = BigDecimal.valueOf(transactions.size());
        return singleTransactionFee.multiplyBy(multiplier);
    }

}
