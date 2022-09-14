package com.company.webservice.core;

import javax.ws.rs.ext.ParamConverter;
import java.time.LocalDate;

public class LocalDateConverter implements ParamConverter<LocalDate> {

    @Override
    public LocalDate fromString(String value) {
        if(value.isEmpty()) {
            return null;
        }

        return LocalDate.parse(value);
    }

    @Override
    public String toString(LocalDate localDate) {
        if(localDate == null) {
            return null;
        }

        return localDate.toString();
    }
}
