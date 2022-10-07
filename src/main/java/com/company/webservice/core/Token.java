package com.company.webservice.core;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Objects;

public class Token implements Principal {
    private String token;

    public Token(String token) {
        this.token = Objects.requireNonNull(token);
    }

    public String getToken() {
        return token;
    }

    @Override
    public String getName() {
        return "token";
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
