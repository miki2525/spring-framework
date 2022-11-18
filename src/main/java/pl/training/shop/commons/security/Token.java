package pl.training.shop.commons.security;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class Token implements Serializable {

    private String qrCode;
    private String code;

}
