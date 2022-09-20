package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class System {
    @ColumnName("id")
    private int id;
    @ColumnName("name")
    private String name;
    @ColumnName("code")
    private String code;
    @ColumnName("parent_system_id")
    private int parentSystemId;

    public System() {}

    public System(int id, String name, String code, int parentSystemId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentSystemId = parentSystemId;
    }

    public System(String name, String code, int parentSystemId) {
        this.name = name;
        this.code = code;
        this.parentSystemId = parentSystemId;
    }

    public System(int id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getParentSystemId() {
        return parentSystemId;
    }

    public void setParentSystemId(int parentSystemId) {
        this.parentSystemId = parentSystemId;
    }
}
