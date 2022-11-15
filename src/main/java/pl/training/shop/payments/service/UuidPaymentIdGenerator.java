package pl.training.shop.payments.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
