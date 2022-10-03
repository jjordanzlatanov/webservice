package com.company.webservice.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public interface Hasher {
    SecureRandom random = new SecureRandom();
    String pepperBase64 = "xkAMHncZ7/TigGdbtb5Fdqlr1BYZ8cbtnwxXj9K4ywM88aL6fkX2a+J1uNthazJjymvawkbrLxLYeOzkagEjmHOBf5UEZc5xAmfzayvEPvwHRAa3klFPAoNXKGa249qBWMqZG4tgDJJrSThiG5SChpbSrlWz8utJb/Zmu2dYVy4=";
    byte[] pepper = pepperBase64.getBytes(StandardCharsets.UTF_8);

    static public String hash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        messageDigest.update(pepper);
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

        byte[] hash = messageDigest.digest();

        return Base64.getEncoder().encodeToString(hash);
    }

    static public String generateSalt() {
        byte[] salt = new byte[128];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }
}
