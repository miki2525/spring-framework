package pl.training.shop.commons.security;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.samstevens.totp.code.HashingAlgorithm.SHA256;

@Service
@RequiredArgsConstructor
public class TokenManager {

    private final SecretGenerator secretGenerator;
    private final QrGenerator qrGenerator;
    private final CodeVerifier codeVerifier;

    boolean verifyTotp(String secret, String code) {
        return codeVerifier.isValidCode(secret, code);
    }

    public String createQr(String secret) {
        try {
            var data = new QrData.Builder()
                    .label("MFA")
                    .secret(secret)
                    .issuer("Shop")
                    .algorithm(SHA256)
                    .digits(6)
                    .period(30)
                    .build();
            byte[] bytes = qrGenerator.generate(data);
            return Utils.getDataUriForImage(bytes, qrGenerator.getImageMimeType());
        } catch (QrGenerationException e) {
            throw new QrInitializationFailedException();
        }
    }

    public String generateSecret() {
        return secretGenerator.generate();
    }

}
