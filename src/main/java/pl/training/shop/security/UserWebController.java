package pl.training.shop.security;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("api/users")
@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UsersService usersService;

    @GetMapping
    public void register(HttpServletResponse httpServletResponse) throws WriterException, IOException {
        httpServletResponse.setHeader("Content-Type", "image/png");
        var matrix = usersService.addUser("marta", "123", "marta@training.pl");
        var stream = httpServletResponse.getOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", stream);
        stream.close();
    }


}
