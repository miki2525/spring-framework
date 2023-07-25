package pl.training.payments.cards.input.rest;

import lombok.Data;

@Data
public class CardTransactionDto {

    private String timestamp;
    private String amount;
    private String type;

}
