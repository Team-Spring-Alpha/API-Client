package br.com.compass.filmes.user.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Md5Test {

    private Md5 md5;

    @BeforeEach
    void setUp() {
        this.md5 = new Md5();
    }

    @Test
    @DisplayName("should encrypted a string to md5")
    void shouldEncryptedToMd5() {
        String encryptedMd5Expected = "cfcd208495d565ef66e7dff9f98764da";
        String encryptedMd5 = md5.ToMd5("0");

        Assertions.assertEquals(encryptedMd5Expected, encryptedMd5);
    }
}