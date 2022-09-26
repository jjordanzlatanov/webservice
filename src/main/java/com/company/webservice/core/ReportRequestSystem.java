package com.company.webservice.core;

public class ReportRequestSystem {
    private String codes;
    private String name;

    public ReportRequestSystem(String codes, String name) {
        this.codes = codes;
        this.name = name;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
