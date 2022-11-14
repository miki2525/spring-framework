package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Aspect
@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher publisher;

    @AfterReturning(value = "@annotation(pl.training.shop.commons.aop.Loggable)", returning = "payment")
    public void onPayment(Payment payment) {
        publisher.publishEvent(payment);
    }

}
