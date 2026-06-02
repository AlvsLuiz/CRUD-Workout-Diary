package model.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordEncryptor {

    public static String hashPassword(String plainTextPassword) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    digest.digest(
                            plainTextPassword.getBytes(
                                    StandardCharsets.UTF_8));

            return Base64.getEncoder()
                    .encodeToString(hash);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao criptografar senha",
                    e);
        }
    }

    public static boolean checkPassword(
            String plainTextPassword,
            String hashedPassword) {

        return hashPassword(plainTextPassword)
                .equals(hashedPassword);
    }
}