package pl.training.shop.commons.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token implements Serializable {

    private String username;
    private String secret;
    private int code;
    private List<Integer> codes;

}
