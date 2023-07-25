package pl.training.payments.domain.cards;

public record CardChargedEvent(CardNumber number, CardTransaction transaction) {

    @Override
    public String toString() {
        return number.value();
    }

}
