package pl.training.shop.payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.training.shop.time.TimeProvider;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator fakePaymentIdGenerator() {
        return new FakePaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator percentagePaymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

    @Bean
    public PaymentRepository jpaRepository() {
        return new JpaPaymentRepository();
    }

    @Scope(BeanDefinition.SCOPE_SINGLETON) // default
    @Bean(name = "paymentService", initMethod = "init", destroyMethod = "destroy")
    public PaymentProcessor paymentProcessor(@Qualifier("uuidPaymentIdGenerator") PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator,
                                             PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider);
    }

}
