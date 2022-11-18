package pl.training.shop.commons.security;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OtpUserService {

    private static final String DEFAULT_ROLE = "ROLE_ADMIN";
    private static final String ISSUER = "Shop";
    private static final String QR_FORMAT = "PNG";

    private final GoogleAuthenticator googleAuthenticator;
    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;

    public byte[] addUser(String name, String password) throws IOException, WriterException {
        if (jpaUserRepository.getByName(name).isPresent()) {
            throw new IllegalStateException();
        }
        var secret= googleAuthenticator.createCredentials(name);
        var user = createUser(name, password, secret);
        jpaUserRepository.save(user);
        return createQrCode(name, secret);
    }

    private OtpUserEntity createUser(String name, String password, GoogleAuthenticatorKey secret) {
        var user = new OtpUserEntity();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(DEFAULT_ROLE);
        user.setEnabled(true);
        user.setSecret(secret.getKey().toCharArray());
        return user;
    }

    private byte[] createQrCode(String name, GoogleAuthenticatorKey secret) throws WriterException, IOException {
        var otpAuthUrl = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(ISSUER, name, secret);
        var matrix = new QRCodeWriter().encode(otpAuthUrl, BarcodeFormat.QR_CODE, 200, 200);
        var bytesStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, QR_FORMAT, bytesStream);
        return bytesStream.toByteArray();
    }

}
