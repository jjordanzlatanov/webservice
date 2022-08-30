package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.beans.ConstructorProperties;

public class Block {
    @ColumnName("id")
    private int id;
    @ColumnName("name")
    private String name;
    @ColumnName("code")
    private String code;

    public Block() {
        super();
    }

    public Block(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Block(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
