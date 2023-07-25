package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import static pl.training.payments.domain.cards.CardTransactionType.FEE;

public class CardHasSufficientBalanceSpecification {

    public boolean check(Money cardBalance, CardTransaction transaction) {
       return transaction.type() == FEE || cardBalance.isGreaterOrEqual(transaction.value());
    }

}
