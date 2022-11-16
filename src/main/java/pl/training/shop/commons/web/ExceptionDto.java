package pl.training.shop.commons.web;

import lombok.Value;

import java.time.Instant;

@Value
public class ExceptionDto {

    Instant timestamp;
    String description;

}
