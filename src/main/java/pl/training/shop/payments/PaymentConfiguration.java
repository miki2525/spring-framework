package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.payments.service.PaymentFeeCalculator;
import pl.training.shop.payments.service.PercentagePaymentFeeCalculator;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentFeeCalculator percentagePaymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

}
