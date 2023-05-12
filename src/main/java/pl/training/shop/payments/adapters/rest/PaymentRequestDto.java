package pl.training.shop.payments.adapters.rest;

import jakarta.validation.constraints.Min;
import lombok.Data;
import pl.training.shop.commons.data.validation.Base;
import pl.training.shop.commons.data.validation.Extended;
import pl.training.shop.commons.data.validation.Money;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = Base.class, message = "{minTextMessage}")
    private Long requestId;
    //@Pattern(regexp = "\\d+ [A-Z]+")
    //@NotNull
    @Money(groups = {Base.class, Extended.class})
    private String value;

}
