package pl.training.payments.domain.cards;

import pl.training.payments.domain.money.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.unmodifiableList;

public class Card {

    private final CardNumber number;
    private final LocalDate expirationDate;
    private final List<CardTransaction> transactions;
    private final CardHasSufficientBalanceSpecification cardHasSufficientBalance = new CardHasSufficientBalanceSpecification();
    private final CardHasNotExpiredSpecification cardHasNotExpired = new CardHasNotExpiredSpecification();
    private final List<Consumer<CardChargedEvent>> eventListeners = new ArrayList<>();

    private Money balance;

    public Card(CardNumber number, LocalDate expirationDate, Money balance, List<CardTransaction> transactions) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.balance = balance;
        this.transactions = transactions;
    }

    public void addTransaction(CardTransaction transaction) {
        if (!cardHasNotExpired.check(transaction.getDate(), expirationDate)) {
            throw new CardHasExpiredException();
        }
        if (!cardHasSufficientBalance.check(balance, transaction)) {
            throw new InsufficientCardBalanceException();
        }
        balance = switch (transaction.type()) {
            case FEE, PAYMENT -> balance.subtract(transaction.value());
        };
        transactions.add(transaction);
        publishEvent(new CardChargedEvent(number, transaction));
    }

    private void publishEvent(CardChargedEvent event) {
        eventListeners.forEach(listener -> listener.accept(event));
    }

    public void addEventsListener(Consumer<CardChargedEvent> listener) {
        eventListeners.add(listener);
    }

    public CardNumber getNumber() {
        return number;
    }

    public Money getBalance() {
        return balance;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public List<CardTransaction> getTransactions() {
        return unmodifiableList(transactions);
    }

}
