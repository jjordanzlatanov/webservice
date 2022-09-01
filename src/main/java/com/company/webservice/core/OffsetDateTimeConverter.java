package com.company.webservice.core;

import javax.ws.rs.ext.ParamConverter;
import java.time.OffsetDateTime;

public class OffsetDateTimeConverter implements ParamConverter<OffsetDateTime> {
    @Override
    public OffsetDateTime fromString(String value) {
        if(value == null) {
            return null;
        }

        return OffsetDateTime.parse(value);
    }

    @Override
    public String toString(OffsetDateTime offsetDateTime) {
        if(offsetDateTime == null) {
            return null;
        }

        return offsetDateTime.toString();
    }
}
