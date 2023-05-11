package pl.training.shop.payments.adapters.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestAdapter {

    @GetMapping("greetings")
    public String sayHello() {
        return "Hi";
    }

}
