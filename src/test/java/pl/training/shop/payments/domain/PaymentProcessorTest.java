package pl.training.shop.payments.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.training.shop.payments.ports.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.training.shop.payments.PaymentFixtures.*;

@ExtendWith(MockitoExtension.class)
class PaymentProcessorTest {

    private static final double PERCENTAGE_VALUE = 0.1;

    private final PaymentRequestDomain paymentRequest = new PaymentRequestDomain(1L, TEST_MONEY_VALUE);
    private PaymentProcessor paymentProcessor;
    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void beforeEach() {
        when(paymentRepository.save(any(PaymentDomain.class))).then(returnsFirstArg());
        paymentProcessor = new PaymentProcessor(TEST_GENERATOR, TEST_FEE_CALCULATOR, paymentRepository, TEST_TIME_PROVIDER);
    }

    @DisplayName("given payment request when process")
    @Nested
    class GivenPaymentRequestWhenProcess {

        @Test
        void then_returns_payment() {
            assertEquals(TEST_PAYMENT, paymentProcessor.process(paymentRequest));
        }

        @Test
        void then_payment_is_persisted() {
            var payment = paymentProcessor.process(paymentRequest);
            verify(paymentRepository).save(payment);
        }

    }

}
