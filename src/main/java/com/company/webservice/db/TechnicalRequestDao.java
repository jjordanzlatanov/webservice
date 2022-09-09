package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequest;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface TechnicalRequestDao {
    @SqlQuery("select * from create_technical_request(:name, :description, :creation_time)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest create(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from read_technical_request(:id, :name, :description, :creation_time)")
    @RegisterBeanMapper(TechnicalRequest.class)
    List<TechnicalRequest> read(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from read_technical_request_single(:id)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest readSingle(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from update_technical_request(:id, :name, :description, :creation_time)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest update(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from delete_technical_request(:id, :name, :description, :creation_time)")
    @RegisterBeanMapper(TechnicalRequest.class)
    List<TechnicalRequest> delete(@BindBean TechnicalRequest technicalRequest);
}
