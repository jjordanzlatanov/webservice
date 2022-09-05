package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestBlockXref {
    @ColumnName("technical_request_id")
    private Integer technical_request_id;
    @ColumnName("block_id")
    private Integer block_id;

    public TechnicalRequestBlockXref() {}

    public TechnicalRequestBlockXref(Integer technical_request_id, Integer block_id) {
        this.technical_request_id = technical_request_id;
        this.block_id = block_id;
    }

    public Integer getTechnical_request_id() {
        return technical_request_id;
    }

    public void setTechnical_request_id(Integer technical_request_id) {
        this.technical_request_id = technical_request_id;
    }

    public Integer getBlock_id() {
        return block_id;
    }

    public void setBlock_id(Integer block_id) {
        this.block_id = block_id;
    }
}
