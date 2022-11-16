package pl.training.shop.payments.adapters.rest;

import lombok.Data;
import pl.training.shop.commons.data.validation.Base;
import pl.training.shop.commons.data.validation.Extended;
import pl.training.shop.commons.data.validation.Money;

import javax.validation.constraints.Min;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = Extended.class)
    private Long requestId;
    /*@Pattern(regexp = "\\d+ [A-Z]+")
    @NotNull*/
    @Money(groups = {Base.class, Extended.class})
    private String value;

}
