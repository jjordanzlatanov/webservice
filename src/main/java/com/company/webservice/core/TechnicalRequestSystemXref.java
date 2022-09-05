package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestSystemXref {
    @ColumnName("technical_request_id")
    private Integer technical_request_id;
    @ColumnName("system_id")
    private Integer system_id;

    public TechnicalRequestSystemXref() {}

    public TechnicalRequestSystemXref(Integer technical_request_id, Integer system_id) {
        this.technical_request_id = technical_request_id;
        this.system_id = system_id;
    }

    public Integer getTechnical_request_id() {
        return technical_request_id;
    }

    public void setTechnical_request_id(Integer technical_request_id) {
        this.technical_request_id = technical_request_id;
    }

    public Integer getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Integer system_id) {
        this.system_id = system_id;
    }
}
