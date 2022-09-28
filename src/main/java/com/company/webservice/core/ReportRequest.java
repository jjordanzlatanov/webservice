package com.company.webservice.core;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportRequest {
    private String name;
    private String description;

    private ArrayList<String> blockCodes;
    private ArrayList<ReportSystem> systems;
    private ArrayList<ReportEmployee> employees;

    private LocalDateTime creationTime;
    private String creationTimeText;

    public ReportRequest() {}

    public ReportRequest(String name, String description, LocalDateTime creationTime, ArrayList<String> blockCodes, ArrayList<ReportSystem> systems, ArrayList<ReportEmployee> employees) {
        this.name = name;
        this.description = description;
        this.creationTime = creationTime;
        this.creationTimeText = creationTime.toString();

        this.blockCodes = blockCodes;
        this.systems = systems;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getBlockCodes() {
        return blockCodes;
    }

    public void setBlockCodes(ArrayList<String> blockCodes) {
        this.blockCodes = blockCodes;
    }

    public ArrayList<ReportSystem> getSystems() {
        return systems;
    }

    public void setSystems(ArrayList<ReportSystem> systems) {
        this.systems = systems;
    }

    public ArrayList<ReportEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<ReportEmployee> employees) {
        this.employees = employees;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTimeText() {
        return creationTimeText;
    }

    public void setCreationTimeText(String creationTimeText) {
        this.creationTimeText = creationTimeText;
    }
}