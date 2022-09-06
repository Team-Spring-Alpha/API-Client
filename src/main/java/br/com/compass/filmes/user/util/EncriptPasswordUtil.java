package br.com.compass.filmes.user.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncriptPasswordUtil {
    public String EncriptPassword(String beEncrypted) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(beEncrypted
                    .getBytes(StandardCharsets.UTF_8)));
            return String.format("%32x", hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
