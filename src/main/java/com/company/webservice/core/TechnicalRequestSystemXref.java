package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestSystemXref {
    @ColumnName("id")
    private int id;
    @ColumnName("technical_request_id")
    private int technicalRequestId;
    @ColumnName("system_id")
    private int systemId;

    public TechnicalRequestSystemXref() {}

    public TechnicalRequestSystemXref(int id, int technicalRequestId, int systemId) {
        this.id = id;
        this.technicalRequestId = technicalRequestId;
        this.systemId = systemId;
    }

    public TechnicalRequestSystemXref(int technicalRequestId, int systemId) {
        this.technicalRequestId = technicalRequestId;
        this.systemId = systemId;
    }

    public TechnicalRequestSystemXref(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTechnicalRequestId() {
        return technicalRequestId;
    }

    public void setTechnicalRequestId(int technicalRequestId) {
        this.technicalRequestId = technicalRequestId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
