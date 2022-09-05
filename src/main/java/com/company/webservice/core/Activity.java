package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Activity {
    @ColumnName("id")
    private Integer id;
    @ColumnName("name")
    private String name;

    public Activity() {}

    public Activity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Activity(String name) {
        this.name = name;
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
}
