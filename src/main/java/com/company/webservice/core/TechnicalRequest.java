package com.company.webservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.LocalDateTime;

public class TechnicalRequest {
    @ColumnName("id")
    private int id;
    @ColumnName("name")
    private String name;
    @ColumnName("description")
    private String description;
    @ColumnName("creation_time")
    private LocalDateTime creation_time;
    @JsonProperty
    private String creation_time_text;

    public TechnicalRequest() {}

    public TechnicalRequest(int id, String name, String description, LocalDateTime creation_time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creation_time = creation_time;
        this.creation_time_text = getCreation_time_text_object(creation_time);
    }

    public TechnicalRequest(String name, String description, LocalDateTime creation_time) {
        this.name = name;
        this.description = description;
        this.creation_time = creation_time;
        this.creation_time_text = getCreation_time_text_object(creation_time);
    }

    public TechnicalRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public String getCreation_time_text() {
        if(creation_time_text == null) {
            return creation_time.toString();
        }

        return creation_time_text;
    }

    public void setCreation_time_text(String creation_time_text) {
        this.creation_time_text = creation_time_text;
    }

    public String getCreation_time_text_object(LocalDateTime creation_time) {
        if(creation_time != null) {
            return creation_time.toString();
        }

        return null;
    }
}
