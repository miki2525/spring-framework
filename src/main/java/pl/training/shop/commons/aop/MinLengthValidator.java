package pl.training.shop.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class MinLengthValidator {

    @Before("execution(* *(@pl.training.shop.commons.aop.MinLength (*)))")
    public void validate(JoinPoint joinPoint) throws NoSuchMethodException {
        var arguments = joinPoint.getArgs();
        var argumentsAnnotations = getArgumentsAnnotations(joinPoint);
        for (int index = 0; index < arguments.length; index++) {
            var argument = (String) arguments[index];
            getLengthAnnotation(argumentsAnnotations[index])
                    .ifPresent(minLength -> validateLength(argument, minLength));
        }
    }

    private void validateLength(String argument, MinLength minLength) {
        if (argument.length() < minLength.value()) {
            throw new IllegalArgumentException("Value is too short, required length is: " + minLength.value());
        }
    }

    private Annotation[][] getArgumentsAnnotations(JoinPoint joinPoint) throws NoSuchMethodException {
        var signature = (MethodSignature) joinPoint.getSignature();
        var methodName = signature.getMethod().getName();
        var parameterTypes = signature.getMethod().getParameterTypes();
        return joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
    }

    private Optional<MinLength> getLengthAnnotation(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(MinLength.class::isInstance)
                .map(MinLength.class::cast)
                .findFirst();
    }

}
