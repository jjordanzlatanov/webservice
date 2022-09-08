package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequestBlockXref;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TechnicalRequestBlockXrefDao {
    @SqlUpdate("select create_technical_request_block_xref(:technical_request_id, :block_id)")
    void create(@BindBean TechnicalRequestBlockXref technicalRequestBlockXref);

    @SqlQuery("select * from read_technical_request_block_xref()")
    @RegisterBeanMapper(TechnicalRequestBlockXref.class)
    List<TechnicalRequestBlockXref> read();

    @SqlQuery("select * from read_technical_request_block_xref_single(:id)")
    @RegisterBeanMapper(TechnicalRequestBlockXref.class)
    TechnicalRequestBlockXref readSingle(@BindBean TechnicalRequestBlockXref technicalRequestBlockXref);

    @SqlUpdate("select update_technical_request_block_xref(:id, :technical_request_id, :block_id)")
    void update(@BindBean TechnicalRequestBlockXref technicalRequestBlockXref);

    @SqlUpdate("select delete_technical_request_block_xref(:id, :technical_request_id, :block_id)")
    void delete(@BindBean TechnicalRequestBlockXref technicalRequestBlockXref);
}