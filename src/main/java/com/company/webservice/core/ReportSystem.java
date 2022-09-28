package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ReportSystem {
    @ColumnName("code")
    private String code;
    @ColumnName("name")
    private String name;
    @ColumnName("parent_system_id")
    private int parentSystemId;

    public ReportSystem() {}

    public ReportSystem(String code, String name, int parentSystemId) {
        this.code = code;
        this.name = name;
        this.parentSystemId = parentSystemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentSystemId() {
        return parentSystemId;
    }

    public void setParentSystemId(int parentSystemId) {
        this.parentSystemId = parentSystemId;
    }
}
