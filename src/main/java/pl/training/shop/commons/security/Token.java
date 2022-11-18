package pl.training.shop.commons.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Token implements Serializable {

    private String qrCode;
    private String code;

}
