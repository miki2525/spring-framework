package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;
import pl.training.shop.payments.ports.PaymentService;

//@Primary
//@Transactional
//@Component
@RequiredArgsConstructor
public class TransactionalPaymentsService implements PaymentService {

    private final PaymentService paymentService;

    @Override
    public PaymentDomain process(PaymentRequestDomain paymentRequest) {
        return paymentService.process(paymentRequest);
    }

    @Override
    public PaymentDomain getById(String id) {
        return paymentService.getById(id);
    }

}
