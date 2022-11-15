package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.commons.aop.Loggable;
import pl.training.shop.commons.aop.MinLength;
import pl.training.shop.time.TimeProvider;

@Transactional
@Log
@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Loggable
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        return paymentsRepository.save(payment);
    }

    private Payment createPayment(FastMoney paymentValue) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }

    private FastMoney calculatePaymentValue(FastMoney paymentValue) {
        return paymentValue.add(paymentFeeCalculator.calculateFee(paymentValue));
    }

    @Override
    public Payment getById(@MinLength String id) {
        return paymentsRepository.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

    public void init() {
        log.info("Initializing PaymentProcessor bean");
    }

    public void destroy() {
        log.info("Destroying PaymentProcessor bean");
    }

}
