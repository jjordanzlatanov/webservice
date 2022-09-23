package com.company.webservice.db;

import com.company.webservice.core.TechnicalRequest;
import org.jdbi.v3.sqlobject.SingleValue;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.ArrayList;
import java.util.List;

public interface TechnicalRequestDao {
    @SqlQuery("select * from create_technical_request(:name, :description, :creationTime)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest create(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from read_technical_request(:id, :name, :description, :creationTime)")
    @RegisterBeanMapper(TechnicalRequest.class)
    List<TechnicalRequest> read(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from read_technical_request_single(:id)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest readSingle(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from update_technical_request(:id, :name, :description, :creationTime)")
    @RegisterBeanMapper(TechnicalRequest.class)
    TechnicalRequest update(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select * from delete_technical_request(:id, :name, :description, :creationTime)")
    @RegisterBeanMapper(TechnicalRequest.class)
    List<TechnicalRequest> delete(@BindBean TechnicalRequest technicalRequest);

    @SqlQuery("select read_system_ids(?)")
    @SingleValue
    ArrayList<Integer> readSystemIds(ArrayList<String> systemCodes);

    @SqlQuery("select read_block_ids(?)")
    @SingleValue
    ArrayList<Integer> readBlockIds(ArrayList<String> blockCodes);

    @SqlQuery("select * from read_report_technical_requests(:query)")
    @RegisterBeanMapper(TechnicalRequest.class)
    ArrayList<TechnicalRequest> readReportTechnicalRequest(@Bind("query") String query);
}
