package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.aop.ExecutionTime;
import pl.training.shop.commons.aop.Loggable;
import pl.training.shop.time.TimeProvider;

@Log
@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @ExecutionTime
    //@Loggable
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

    public void init() {
        log.info("Initializing PaymentProcessor bean");
    }

    public void destroy() {
        log.info("Destroying PaymentProcessor bean");
    }

}
