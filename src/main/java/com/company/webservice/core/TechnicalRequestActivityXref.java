package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestActivityXref {
    @ColumnName("id")
    private int id;
    @ColumnName("technical_request_id")
    private int technicalRequestId;
    @ColumnName("activity_id")
    private int activityId;
    @ColumnName("employee_id")
    private int employeeId;

    public TechnicalRequestActivityXref() {}

    public TechnicalRequestActivityXref(int id, int technicalRequestId, int activityId, int employeeId) {
        this.id = id;
        this.technicalRequestId = technicalRequestId;
        this.activityId = activityId;
        this.employeeId = employeeId;
    }

    public TechnicalRequestActivityXref(int technicalRequestId, int activityId, int employeeId) {
        this.technicalRequestId = technicalRequestId;
        this.activityId = activityId;
        this.employeeId = employeeId;
    }

    public TechnicalRequestActivityXref(int id) {
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
