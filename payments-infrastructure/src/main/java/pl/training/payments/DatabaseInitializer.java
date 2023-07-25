package pl.training.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.payments.domain.cards.CardFactory;
import pl.training.payments.domain.cards.CardRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.YEARS;

@Transactional
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {

    private final CardFactory cardFactory;
    private final CardRepository cardRepository;

    @Override
    public void run(ApplicationArguments args) {
        var expirationDate =  LocalDate.now().plus(1, YEARS);
        var card = cardFactory.create("1234567890123456", expirationDate, 10_000, "PLN", new ArrayList<>());
        cardRepository.save(card);
    }

}
