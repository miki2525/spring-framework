package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import java.util.List;

public interface TransactionFeePolicy {

    Money execute(List<CardTransaction> transactions);

}
