package pl.training.shop.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static pl.training.shop.commons.aop.Annotations.getTargetMethod;

@Aspect
@Component
public class MinLengthValidator {

    @Before("execution(* *(@pl.training.shop.commons.aop.MinLength (*)))")
    public void validate(JoinPoint joinPoint) throws NoSuchMethodException {
        var arguments = joinPoint.getArgs();
        var argumentsAnnotations = getTargetMethod(joinPoint).getParameterAnnotations();
        for (int index = 0; index < arguments.length; index++) {
            var argument = (String) arguments[index];
            Annotations.findAnnotation(argumentsAnnotations[index], MinLength.class)
                    .ifPresent(minLength -> validateLength(argument, minLength));
        }
    }

    private void validateLength(String argument, MinLength minLength) {
        if (argument.length() < minLength.value()) {
            throw new IllegalArgumentException("Value is too short, required length is: " + minLength.value());
        }
    }

}
