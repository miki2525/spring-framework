package pl.training.shop.payments.service;

import pl.training.shop.payments.service.PaymentDomain;

import java.util.Optional;

public interface PaymentRepository {

    PaymentDomain save(PaymentDomain paymentDomain);

    Optional<PaymentDomain> getById(String id);

}
