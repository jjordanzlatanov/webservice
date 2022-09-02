package com.company.webservice.core;

import javax.ws.rs.ext.ParamConverter;
import java.time.LocalDateTime;

public class LocalDateTImeConverter implements ParamConverter<LocalDateTime> {
    @Override
    public LocalDateTime fromString(String value) {
        if(value.isEmpty()) {
            return null;
        }

        return LocalDateTime.parse(value, DTF.get());
    }

    @Override
    public String toString(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return null;
        }

        return localDateTime.toString();
    }
}
