package com.company.webservice.core;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportRequest {
    private String name;
    private String description;
    ArrayList<String> blockCodes;
    ArrayList<ReportRequestSystem> systems;
    ArrayList<Employee> employees;
    LocalDateTime creationTime;

    
}
