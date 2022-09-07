package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestSystemXref {
    @ColumnName("id")
    private int id;
    @ColumnName("technical_request_id")
    private int technical_request_id;
    @ColumnName("system_id")
    private int system_id;

    public TechnicalRequestSystemXref() {}

    public TechnicalRequestSystemXref(int id, int technical_request_id, int system_id) {
        this.id = id;
        this.technical_request_id = technical_request_id;
        this.system_id = system_id;
    }

    public TechnicalRequestSystemXref(int technical_request_id, int system_id) {
        this.technical_request_id = technical_request_id;
        this.system_id = system_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTechnical_request_id() {
        return technical_request_id;
    }

    public void setTechnical_request_id(int technical_request_id) {
        this.technical_request_id = technical_request_id;
    }

    public int getSystem_id() {
        return system_id;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }
}
