package com.company.webservice.core;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TechnicalRequestBlockXref {
    @ColumnName("id")
    private int id;
    @ColumnName("technical_request_id")
    private int technicalRequestId;
    @ColumnName("block_id")
    private int blockId;

    public TechnicalRequestBlockXref() {}

    public TechnicalRequestBlockXref(int id, int technicalRequestId, int blockId) {
        this.id = id;
        this.technicalRequestId = technicalRequestId;
        this.blockId = blockId;
    }

    public TechnicalRequestBlockXref(int technicalRequestId, int blockId) {
        this.technicalRequestId = technicalRequestId;
        this.blockId = blockId;
    }

    public TechnicalRequestBlockXref(int id) {
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

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
