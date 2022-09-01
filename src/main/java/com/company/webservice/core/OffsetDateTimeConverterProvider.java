package com.company.webservice.core;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;

@Provider
public class OffsetDateTimeConverterProvider implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if(!type.equals(OffsetDateTime.class)) {
            return null;
        }

        return (ParamConverter<T>) new OffsetDateTimeConverter();
    }
}
