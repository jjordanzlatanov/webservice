package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ReportEmployee {
    @ColumnName("first_name")
    private String firstName;
    @ColumnName("surname")
    private String surname;
    @ColumnName("last_name")
    private String lastName;
    @ColumnName("activity_name")
    private String activityName;

    public ReportEmployee() {}

    public ReportEmployee(String firstName, String surname, String lastName, String activityName) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.activityName = activityName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
