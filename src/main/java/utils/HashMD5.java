package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HashMD5 {

    /**
     * @param password
     * @return MD5 Hash of password
     * @throws NoSuchAlgorithmException
     */
    public static String HashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(password.getBytes(UTF_8));
        return String.format("%032x%n", new BigInteger(1, digest));
    }
}
