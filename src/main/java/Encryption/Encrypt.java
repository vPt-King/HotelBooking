package Encryption;

import java.util.Base64;

/**
 *
 * @author TDT Computer
 */
public class Encrypt {
    public static String encrypt(String plainText) {
        byte[] bytes = plainText.getBytes();
        String encryptedText = Base64.getEncoder().encodeToString(bytes);
        return encryptedText;
    }
    
    public static String decrypt(String encryptedText) {
        byte[] bytes = Base64.getDecoder().decode(encryptedText);
        String plainText = new String(bytes);
        return plainText;
    }
}
