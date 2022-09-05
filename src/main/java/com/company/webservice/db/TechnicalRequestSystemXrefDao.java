package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequestSystemXref;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TechnicalRequestSystemXrefDao {
    @SqlUpdate("select create_technical_request_system_xref(:technical_request_id, :system_id)")
    void create(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);

    @SqlQuery("select * from read_technical_request_system_xref()")
    @RegisterBeanMapper(TechnicalRequestSystemXref.class)
    List<TechnicalRequestSystemXref> read();

    @SqlUpdate("select update_technical_request_system_xref(:technical_request_id, :system_id)")
    void update(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);

    @SqlUpdate("select delete_technical_request_block_xref(:technical_request_id, :system_id)")
    void delete(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);
}
