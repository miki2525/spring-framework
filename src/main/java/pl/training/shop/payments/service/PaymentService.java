package pl.training.shop.payments.service;

public interface PaymentService {

    PaymentDomain process(PaymentRequestDomain paymentRequest);

    PaymentDomain getById(String id);

}
