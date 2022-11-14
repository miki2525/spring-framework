package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log
public class PaymentEventListener {

    @Async
    @EventListener
    public void onPayment(Payment payment) {
        log.info("New payment event: " + payment);
    }

}
