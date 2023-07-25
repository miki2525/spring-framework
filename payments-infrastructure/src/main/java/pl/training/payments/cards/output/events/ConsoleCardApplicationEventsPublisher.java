package pl.training.payments.cards.output.events;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pl.training.payments.application.output.events.CardChargedApplicationEvent;
import pl.training.payments.application.output.events.CardApplicationEventsPublisher;

@Log
@Service
public class ConsoleCardApplicationEventsPublisher implements CardApplicationEventsPublisher {

    @Override
    public void publish(CardChargedApplicationEvent event) {
        log.info("New application event: " + event);
    }

}
