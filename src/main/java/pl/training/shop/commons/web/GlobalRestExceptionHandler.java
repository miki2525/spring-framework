package pl.training.shop.commons.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalRestExceptionHandler {

    private final RestExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        log.warning("Exception: " + exception.getClass().getName());
        return responseBuilder.build(exception, INTERNAL_SERVER_ERROR, locale);
    }

}
