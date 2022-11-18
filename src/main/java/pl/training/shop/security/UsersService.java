package pl.training.shop.security;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.zxing.qrcode.QRCodeWriter;
import pl.training.shop.users.adapters.persistence.UserEntity;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final PasswordEncoder passwordEncoder;
    private final GoogleAuthenticator googleAuthenticator;
    private final JpaUserRepository jpaUserRepository;

    public BitMatrix addUser(String name, String password, String email) throws WriterException {
        if (jpaUserRepository.getByName(name).isPresent()) {
            throw new IllegalStateException();
        }
        var secret= googleAuthenticator.createCredentials(name);
        var otpAuthUrl = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("Shop", name, secret);



        var user = new UserEntity();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);
        user.setSecret(secret.getKey());
        user.setTwoFactorAuthentication(true);
        jpaUserRepository.save(user);
        return new QRCodeWriter().encode(otpAuthUrl, BarcodeFormat.QR_CODE, 200, 200);
    }


}
