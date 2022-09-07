package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Activity {
    @ColumnName("id")
    private int id;
    @ColumnName("name")
    private String name;

    public Activity() {}

    public Activity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Activity(String name) {
        this.name = name;
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
}
