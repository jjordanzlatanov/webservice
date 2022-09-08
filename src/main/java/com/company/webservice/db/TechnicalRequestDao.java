package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequest;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TechnicalRequestDao {
    @SqlUpdate("select create_technical_request(:name, :description, :creation_time)")
    void create(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from read_technical_request()")
    @RegisterBeanMapper(TechnicalRequest.class)
    List<TechnicalRequest> read();

    @SqlQuery("select * from read_technical_request_single(:id)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest readSingle(@BindBean TechnicalRequest technicalRequest);

    @SqlUpdate("select update_technical_request(:id, :name, :description, :creation_time)")
    void update(@BindBean TechnicalRequest technicalRequest);

    @SqlUpdate("select delete_technical_request(:id, :name, :description, :creation_time)")
    void delete(@BindBean TechnicalRequest technicalRequest);
}
