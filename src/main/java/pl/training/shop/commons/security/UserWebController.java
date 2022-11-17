package pl.training.shop.commons.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("api/users/me")
@RestController
public class UserWebController {

    @GetMapping
    public UserDetails getInfo(Authentication authentication, Principal principal) {
        // var authentication = SecurityContextHolder.getContext().getAuthentication();
        return (ShopUserDetails) authentication.getPrincipal();
    }

}
