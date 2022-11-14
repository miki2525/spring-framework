package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1_00)
@Aspect
@Component
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    @Pointcut("execution(* pl.training.*.payments.PaymentProc*.pr*(..))")
    //@Pointcut("execution(pl.training.shop.payments.Payment pl.training.shop.payments.PaymentProcessor.process(pl.training.shop.payments.PaymentRequest))")
    //@Pointcut("@annotation(pl.training.shop.commons.aop.Loggable)")
    //@Pointcut("bean(paymentService)")
    void process() {
    }

    @Before(value = "process() && args(paymentRequest)")
    public void onStart(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        // var paymentRequest = (PaymentRequest) joinPoint.getArgs()[0];
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "process()", returning = "payment")
    public void onSuccess(Payment payment) {
        log.info("A new payment of %s has been initiated".formatted(payment.getValue()));
    }

    @AfterThrowing(value = "process()", throwing = "exception")
    public void onFailure(JoinPoint joinPoint, RuntimeException exception) {
        log.info("Payment processing failed with exception: %s (method: %s)".formatted(exception.getClass().getSimpleName(), joinPoint.getSignature()));
    }

    @After("process()")
    public void onFinish() {
        log.info("Payment processing complete");
    }

}
