package pl.training.shop.commons.security;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("users")
@Controller
@RequiredArgsConstructor
public class OtpUserWebController {

    private final OtpUserService otpUserService;

    @GetMapping
    public String showUserForm(Model model) {
        var otpUserViewModel = new OtpUserViewModel();
        model.addAttribute("user", otpUserViewModel);
        return "users/user-form";
    }

    @PostMapping
    public void register(@ModelAttribute("user") OtpUserViewModel otpUserViewModel, HttpServletResponse httpServletResponse) throws IOException, WriterException {
        var qrCode = otpUserService.addUser(otpUserViewModel.getName(), otpUserViewModel.getPassword());
        httpServletResponse.setHeader("Content-Type", "image/png");
        var stream = httpServletResponse.getOutputStream();
        stream.write(qrCode);
        stream.close();
    }

}
