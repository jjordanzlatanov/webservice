package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestBlockXref {
    @ColumnName("id")
    private int id;
    @ColumnName("technical_request_id")
    private int technical_request_id;
    @ColumnName("block_id")
    private int block_id;

    public TechnicalRequestBlockXref() {}

    public TechnicalRequestBlockXref(int id, int technical_request_id, int block_id) {
        this.id = id;
        this.technical_request_id = technical_request_id;
        this.block_id = block_id;
    }

    public TechnicalRequestBlockXref(int technical_request_id, int block_id) {
        this.technical_request_id = technical_request_id;
        this.block_id = block_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTechnical_request_id() {
        return technical_request_id;
    }

    public void setTechnical_request_id(int technical_request_id) {
        this.technical_request_id = technical_request_id;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }
}
