package pl.training.payments.cards.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.payments.application.cards.CardNotFoundException;
import pl.training.payments.common.rest.ExceptionDto;
import pl.training.payments.common.rest.RestExceptionResponseBuilder;

import java.util.Locale;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(HIGHEST_PRECEDENCE)
@ControllerAdvice("pl.training.payments.cards.input.rest")
@RequiredArgsConstructor
public class PaymentRestExceptionHandler {

    private final RestExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ExceptionDto> onCardNotFoundException(CardNotFoundException cardNotFoundException, Locale locale) {
        return responseBuilder.build(cardNotFoundException, NOT_FOUND, locale);
    }

}
