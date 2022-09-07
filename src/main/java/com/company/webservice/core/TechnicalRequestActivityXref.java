package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestActivityXref {
    @ColumnName("id")
    private int id;
    @ColumnName("technical_request_id")
    private int technical_request_id;
    @ColumnName("activity_id")
    private int activity_id;
    @ColumnName("employee_id")
    private int employee_id;

    public TechnicalRequestActivityXref() {}

    public TechnicalRequestActivityXref(int id, int technical_request_id, int activity_id, int employee_id) {
        this.id = id;
        this.technical_request_id = technical_request_id;
        this.activity_id = activity_id;
        this.employee_id = employee_id;
    }

    public TechnicalRequestActivityXref(int technical_request_id, int activity_id, int employee_id) {
        this.technical_request_id = technical_request_id;
        this.activity_id = activity_id;
        this.employee_id = employee_id;
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

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
}
