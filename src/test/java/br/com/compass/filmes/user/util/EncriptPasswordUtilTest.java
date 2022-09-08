package br.com.compass.filmes.user.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

class EncriptPasswordUtilTest {

    private EncriptPasswordUtil encriptPasswordUtil;

    @BeforeEach
    void setUp() {
        this.encriptPasswordUtil = new EncriptPasswordUtil();
    }

    @Test
    @DisplayName("should encrypted a string to md5")
    void shouldEncryptedToMd5() {
        String encryptedMd5Expected = "cfcd208495d565ef66e7dff9f98764da";
        String encryptedMd5 = encriptPasswordUtil.Encript("0");

        Assertions.assertEquals(encryptedMd5Expected, encryptedMd5);
    }
}