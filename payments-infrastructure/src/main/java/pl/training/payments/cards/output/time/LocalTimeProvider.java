package pl.training.payments.cards.output.time;

import org.springframework.stereotype.Service;
import pl.training.payments.application.output.time.TimeProvider;

import java.time.ZonedDateTime;

@Service
public class LocalTimeProvider implements TimeProvider {

    @Override
    public ZonedDateTime getTimestamp() {
        return ZonedDateTime.now();
    }

}
