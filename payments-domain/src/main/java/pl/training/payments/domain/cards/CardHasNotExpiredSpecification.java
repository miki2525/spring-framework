package pl.training.payments.domain.cards;

import java.time.LocalDate;

public class CardHasNotExpiredSpecification {

    public boolean check(LocalDate localDate, LocalDate cardExpirationDate) {
        return localDate.isBefore(cardExpirationDate);
    }

}
