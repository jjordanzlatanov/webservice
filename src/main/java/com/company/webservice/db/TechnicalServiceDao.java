package com.company.webservice.db;

import com.company.webservice.core.TechnicalService;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TechnicalServiceDao {
    @SqlUpdate("select create_technical_service(:name, :description, :creation_time)")
    void create(@BindBean TechnicalService technicalService);

    @SqlQuery("select * from read_technical_service()")
    @RegisterBeanMapper(TechnicalService.class)
    List<TechnicalService> read();

    @SqlUpdate("select update_technical_service(:id, :name, :description, :creation_time)")
    void update(@BindBean TechnicalService technicalService);

    @SqlUpdate("select delete_technical_service(:id, :name, :description, :creation_time)")
    void delete(@BindBean TechnicalService technicalService);
}
