package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HashMD5 {

    public static String HashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // Calculate Message Digest as bytes:
        byte[] digest = md5.digest(password.getBytes(UTF_8));
        // Convert to 32-char long String:
        return String.format("%032x%n", new BigInteger(1, digest));
    }
}
