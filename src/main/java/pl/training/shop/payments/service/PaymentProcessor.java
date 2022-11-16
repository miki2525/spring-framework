package pl.training.shop.payments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.commons.aop.Loggable;
import pl.training.shop.commons.aop.MinLength;
import pl.training.shop.payments.service.integration.PaymentRepository;
import pl.training.shop.time.TimeProvider;

@Transactional
@Service("paymentService")
@Log
@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Loggable
    @Override
    public PaymentDomain process(PaymentRequestDomain paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        return paymentsRepository.save(payment);
    }

    private PaymentDomain createPayment(Money paymentValue) {
        return PaymentDomain.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatusDomain.STARTED)
                .build();
    }

    private Money calculatePaymentValue(Money paymentValue) {
        var paymentFee = paymentFeeCalculator.calculateFee(paymentValue);
        return paymentValue.add(paymentFee);
    }

    @Override
    public PaymentDomain getById(@MinLength String id) {
        return paymentsRepository.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
