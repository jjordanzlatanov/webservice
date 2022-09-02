package com.company.webservice.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DTF {
    static public DateTimeFormatter get() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    }

    static public LocalDateTime now() {
        return LocalDateTime.parse(LocalDateTime.now().format(get()));
    }
}
