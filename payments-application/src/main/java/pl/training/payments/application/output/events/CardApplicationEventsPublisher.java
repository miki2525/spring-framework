package pl.training.payments.application.output.events;

public interface CardApplicationEventsPublisher {

    void publish(CardChargedApplicationEvent event);

}
