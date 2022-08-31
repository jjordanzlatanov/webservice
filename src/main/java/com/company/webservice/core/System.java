package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class System {
    @ColumnName("id")
    private Integer id;
    @ColumnName("name")
    private String name;
    @ColumnName("code")
    private String code;
    @ColumnName("parent_system_id")
    private Integer parent_system_id;

    public System() {}

    public System(Integer id, String name, String code, Integer parent_system_id) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parent_system_id = parent_system_id;
    }

    public System(String name, String code, Integer parent_system_id) {
        this.name = name;
        this.code = code;
        this.parent_system_id = parent_system_id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParent_system_id() {
        return parent_system_id;
    }

    public void setParent_system_id(Integer parent_system_id) {
        this.parent_system_id = parent_system_id;
    }
}
