package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequestActivityXref;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TechnicalRequestActivityXrefDao {
    @SqlUpdate("select create_technical_request_activity_xref(:technical_request_id, :activity_id, :employee_id)")
    void create(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);

    @SqlQuery("select * from read_technical_request_activity_xref()")
    @RegisterBeanMapper(TechnicalRequestActivityXref.class)
    List<TechnicalRequestActivityXref> read();

    @SqlUpdate("select update_technical_request_activity_xref(:id, :technical_request_id, :activity_id, :employee_id)")
    void update(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);

    @SqlUpdate("select delete_technical_request_activity_xref(:id, :technical_request_id, :activity_id, :employee_id)")
    void delete(@BindBean TechnicalRequestActivityXref technicalRequestActivityXref);
}
