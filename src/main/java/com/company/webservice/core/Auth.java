package com.company.webservice.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public interface Auth {
    SecureRandom random = new SecureRandom();
    String pepperBase64 = "xkAMHncZ7/TigGdbtb5Fdqlr1BYZ8cbtnwxXj9K4ywM88aL6fkX2a+J1uNthazJjymvawkbrLxLYeOzkagEjmHOBf5UEZc5xAmfzayvEPvwHRAa3klFPAoNXKGa249qBWMqZG4tgDJJrSThiG5SChpbSrlWz8utJb/Zmu2dYVy4=";
    byte[] pepper = pepperBase64.getBytes(StandardCharsets.UTF_8);

    String secret = "LdTG1AGvEEfdHFvmCzaoMjeP1Tb+Xyi3B/yamny+98IPb+Lj5M6DJwCqoPUNoVjzTuVUJyV+C+6n5ssArB95pSlXaWh9gVn9A8n9hbXMbNjz7uX8bGl0p34SsSxZlm+aCTSKHivNkHuBd0ok4WEBivWnKs1sK3UVqGI3KTjYNow=";
    Algorithm algorithm = Algorithm.HMAC512(secret);

    static String hash(String salt, String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        messageDigest.update(pepper);
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

        byte[] hash = messageDigest.digest();

        return Base64.getEncoder().encodeToString(hash);
    }

    static String generateSalt() {
        byte[] salt = new byte[128];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    static String generateToken(String username, String passwordHash) throws IllegalArgumentException, JWTCreationException {
        byte[] bytes = new byte[128];
        random.nextBytes(bytes);

        return JWT.create().withIssuer("auth0").withClaim("username", username).withClaim("password", passwordHash).withClaim("random", Base64.getEncoder().encodeToString(bytes)).sign(algorithm);
    }

    static boolean verifyToken(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer("auth0").build().verify(token);
        return true;
    }
}
