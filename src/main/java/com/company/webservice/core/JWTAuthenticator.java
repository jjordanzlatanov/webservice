package com.company.webservice.core;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class JWTAuthenticator implements Authenticator<String, Token> {
    @Override
    public Optional<Token> authenticate(String token) throws AuthenticationException {
        if(Auth.verifyToken(token)) {
            return Optional.of(new Token(token));
        }

        return Optional.empty();
    }
}
