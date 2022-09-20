package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Employee {
    @ColumnName("id")
    private int id;
    @ColumnName("first_name")
    private String firstName;
    @ColumnName("surname")
    private String surname;
    @ColumnName("last_name")
    private String lastName;
    @ColumnName("pin")
    private int pin;

    public Employee() {}

    public Employee(int id, String firstName, String surname, String lastName, int pin) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.pin = pin;
    }

    public Employee(String firstName, String surname, String lastName, int pin) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
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

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
