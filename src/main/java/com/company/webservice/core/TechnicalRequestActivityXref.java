package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestActivityXref {
    @ColumnName("technical_request_id")
    private Integer technical_request_id;
    @ColumnName("activity_id")
    private Integer activity_id;
    @ColumnName("employee_id")
    private Integer employee_id;

    public TechnicalRequestActivityXref() {}

    public TechnicalRequestActivityXref(Integer technical_request_id, Integer activity_id, Integer employee_id) {
        this.technical_request_id = technical_request_id;
        this.activity_id = activity_id;
        this.employee_id = employee_id;
    }

    public Integer getTechnical_request_id() {
        return technical_request_id;
    }

    public void setTechnical_request_id(Integer technical_request_id) {
        this.technical_request_id = technical_request_id;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
}
