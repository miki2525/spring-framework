package pl.training.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

// @Order(1_000)
@Aspect
@Component
@Log
public class ExecutionTimeLogger implements Ordered {

    @Around("@annotation(ExecutionTime)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = joinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info("Execution time %d ns for method %s".formatted(totalTime, joinPoint.getSignature()));
        return result;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

}
