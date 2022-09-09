package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequestActivityXref;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface TechnicalRequestActivityXrefDao {
    @SqlQuery("select * from create_technical_request_activity_xref(:technical_request_id, :activity_id, :employee_id)")
    @RegisterBeanMapper(TechnicalRequestActivityXref.class)
    TechnicalRequestActivityXref create(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);

    @SqlQuery("select * from read_technical_request_activity_xref(:id, :technical_request_id, :activity_id, :employee_id)")
    @RegisterBeanMapper(TechnicalRequestActivityXref.class)
    List<TechnicalRequestActivityXref> read(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);

    @SqlQuery("select * from read_technical_request_activity_xref_single(:id)")
    @RegisterBeanMapper(TechnicalRequestActivityXref.class)
    TechnicalRequestActivityXref readSingle(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);

    @SqlQuery("select * from update_technical_request_activity_xref(:id, :technical_request_id, :activity_id, :employee_id)")
    @RegisterBeanMapper(TechnicalRequestActivityXref.class)
    TechnicalRequestActivityXref update(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);

    @SqlQuery("select * from delete_technical_request_activity_xref(:id, :technical_request_id, :activity_id, :employee_id)")
    @RegisterBeanMapper(TechnicalRequestActivityXref.class)
    List<TechnicalRequestActivityXref> delete(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);
}
