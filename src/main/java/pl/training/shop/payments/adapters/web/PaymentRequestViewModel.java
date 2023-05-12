package pl.training.shop.payments.adapters.web;

import lombok.Data;
import pl.training.shop.commons.data.validation.Money;

import java.io.Serializable;

@Data
public class PaymentRequestViewModel implements Serializable {

    @Money
    private String value;

}
