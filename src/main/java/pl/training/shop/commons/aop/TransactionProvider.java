package pl.training.shop.commons.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionProvider {

    private final PlatformTransactionManager transactionManager;

    @Around("@annotation(pl.training.shop.commons.aop.Atomic) || within(@pl.training.shop.commons.aop.Atomic *)")
    public Object runWithTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        var atomic = getAtomicAnnotation(joinPoint);
        var transactionDefinition = getTransactionDefinition(atomic);
        var transaction = transactionManager.getTransaction(transactionDefinition);
        try {
            var result = joinPoint.proceed();
            transactionManager.commit(transaction);
            return result;
        } catch (Throwable throwable) {
            transaction.setRollbackOnly();
            throw throwable;
        }
    }

    private Atomic getAtomicAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        var classAnnotation = joinPoint.getTarget().getClass().getAnnotation(Atomic.class);
        var signature = (MethodSignature) joinPoint.getSignature();
        var methodName = signature.getMethod().getName();
        var parameterTypes = signature.getMethod().getParameterTypes();
        var methodAnnotation = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getAnnotation(Atomic.class);
        return methodAnnotation != null ? methodAnnotation : classAnnotation;
    }

    private DefaultTransactionDefinition getTransactionDefinition(Atomic atomic) {
        var transactionDefinition = new DefaultTransactionDefinition();
        var timout = atomic.timeout();
        if (timout != 0) {
            transactionDefinition.setTimeout(timout);
        }
        return transactionDefinition;
    }

}
