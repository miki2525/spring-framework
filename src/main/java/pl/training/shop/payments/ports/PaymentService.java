package pl.training.shop.payments.ports;

import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;

public interface PaymentService {

    PaymentDomain process(PaymentRequestDomain paymentRequest);

    PaymentDomain getById(String id);

}
