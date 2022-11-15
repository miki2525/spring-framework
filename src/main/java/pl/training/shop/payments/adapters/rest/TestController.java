package pl.training.shop.payments.adapters.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("greetings")
    public String sayHello() {
        return "Hello";
    }

}
