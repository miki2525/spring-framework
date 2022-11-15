package pl.training.shop.payments.ports;

import pl.training.shop.payments.domain.PaymentDomain;

import java.util.Optional;

public interface PaymentRepository {

    PaymentDomain save(PaymentDomain paymentDomain);

    Optional<PaymentDomain> getById(String id);

}
