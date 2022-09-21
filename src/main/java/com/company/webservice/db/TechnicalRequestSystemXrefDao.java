package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequestSystemXref;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface TechnicalRequestSystemXrefDao {
    @SqlQuery("select * from create_technical_request_system_xref(:technicalRequestId, :systemId)")
    @RegisterBeanMapper(TechnicalRequestSystemXref.class)
    TechnicalRequestSystemXref create(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);

    @SqlQuery("select * from read_technical_request_system_xref(:id, :technicalRequestId, :systemId)")
    @RegisterBeanMapper(TechnicalRequestSystemXref.class)
    List<TechnicalRequestSystemXref> read(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);

    @SqlQuery("select * from read_technical_request_system_xref_single(:id)")
    @RegisterBeanMapper(TechnicalRequestSystemXref.class)
    TechnicalRequestSystemXref readSingle(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);

    @SqlQuery("select * from update_technical_request_system_xref(:id, :technicalRequestId, :systemId)")
    @RegisterBeanMapper(TechnicalRequestSystemXref.class)
    TechnicalRequestSystemXref update(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);

    @SqlQuery("select * from delete_technical_request_system_xref(:id, :technicalRequestId, :systemId)")
    @RegisterBeanMapper(TechnicalRequestSystemXref.class)
    List<TechnicalRequestSystemXref> delete(@BindBean TechnicalRequestSystemXref technicalRequestSystemXref);
}
