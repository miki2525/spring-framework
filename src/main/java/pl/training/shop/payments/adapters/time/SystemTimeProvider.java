package pl.training.shop.payments.adapters.time;

import pl.training.shop.payments.ports.TimeProvider;

import java.time.Instant;

public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
