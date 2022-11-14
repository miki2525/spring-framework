package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.PaymentService;

@Log
public class Application {

    private static final String APPLICATION_CONFIGURATION = "application.xml";
    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static void main(String[] args) {
        try (var container = new ClassPathXmlApplicationContext(APPLICATION_CONFIGURATION)) {
            var paymentService = container.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
