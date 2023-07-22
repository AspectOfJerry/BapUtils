package net.jerrydev.baputils.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

// Party Takeover command
@Deprecated
public class PtoEncrypt {
    @Deprecated
    public static String encryptString(String plainText, int SECRET_KEY) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKeyBytes(SECRET_KEY), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Deprecated
    public static String decryptString(String encryptedText, int SECRET_KEY) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKeyBytes(SECRET_KEY), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    @Deprecated
    private static byte[] getKeyBytes(int timestamp) {
        long paddedTimestamp = (long) timestamp << 32; // Left shift the timestamp by 32 bits
        byte[] keyBytes = new byte[16]; // AES-128 key length

        for (int i = 0; i < 16; i++) {
            keyBytes[i] = (byte) ((paddedTimestamp >> (i * 8)) & 0xFF);
        }

        return keyBytes;
    }

}
