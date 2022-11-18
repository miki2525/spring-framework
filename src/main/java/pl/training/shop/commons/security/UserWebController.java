package pl.training.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/users")
@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UsersService usersService;

    @GetMapping
    public String register(Model model) {
        usersService.addUser("marta", "123", "marta@training.pl");
        var token = usersService.createToken("marta");
        model.addAttribute("token", token);
        return "users/configure-2fa";
    }


}
