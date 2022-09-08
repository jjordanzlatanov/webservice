package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Employee {
    @ColumnName("id")
    private int id;
    @ColumnName("first_name")
    private String first_name;
    @ColumnName("surname")
    private String surname;
    @ColumnName("last_name")
    private String last_name;
    @ColumnName("pin")
    private int pin;

    public Employee() {}

    public Employee(int id, String first_name, String surname, String last_name, int pin) {
        this.id = id;
        this.first_name = first_name;
        this.surname = surname;
        this.last_name = last_name;
        this.pin = pin;
    }

    public Employee(String first_name, String surname, String last_name, int pin) {
        this.first_name = first_name;
        this.surname = surname;
        this.last_name = last_name;
        this.pin = pin;
    }

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
