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
    private LocalDateTime creationTime;
    @JsonProperty
    private String creationTimeText;

    public TechnicalRequest() {}

    public TechnicalRequest(int id, String name, String description, LocalDateTime creationTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationTime = creationTime;
        this.creationTimeText = getCreation_time_text_object(creationTime);
    }

    public TechnicalRequest(String name, String description, LocalDateTime creationTime) {
        this.name = name;
        this.description = description;
        this.creationTime = creationTime;
        this.creationTimeText = getCreation_time_text_object(creationTime);
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTimeText() {
        if(creationTimeText == null) {
            return creationTime.toString();
        }

        return creationTimeText;
    }

    public void setCreationTimeText(String creationTimeText) {
        this.creationTimeText = creationTimeText;
    }

    public String getCreation_time_text_object(LocalDateTime creation_time) {
        if(creation_time != null) {
            return creation_time.toString();
        }

        return null;
    }
}
