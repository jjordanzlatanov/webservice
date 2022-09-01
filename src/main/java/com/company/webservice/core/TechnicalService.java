package com.company.webservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.OffsetDateTime;

public class TechnicalService {
    @ColumnName("id")
    private Integer id;
    @ColumnName("name")
    private String name;
    @ColumnName("description")
    private String description;
    @ColumnName("creation_time")
    private OffsetDateTime creation_time;

    public TechnicalService() {}

    public TechnicalService(Integer id, String name, String description, OffsetDateTime creation_time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creation_time = creation_time;
    }

    public TechnicalService(String name, String description, OffsetDateTime creation_time) {
        this.name = name;
        this.description = description;
        this.creation_time = creation_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public OffsetDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(OffsetDateTime creation_time) {
        this.creation_time = creation_time;
    }
}
