package com.company.webservice.db;

import com.company.webservice.core.ReportEmployee;
import com.company.webservice.core.ReportSystem;
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

    @SqlQuery("select read_system_child_ids(?)")
    @SingleValue
    ArrayList<Integer> readSystemChildIds(ArrayList<String> systemCodes);

    @SqlQuery("select * from read_report_technical_requests(:query)")
    @RegisterBeanMapper(TechnicalRequest.class)
    ArrayList<TechnicalRequest> readReportTechnicalRequest(@Bind("query") String query);

    @SqlQuery("select read_report_block_codes(:technicalRequestId)")
    @SingleValue
    ArrayList<String> readBlockCodes(@Bind("technicalRequestId") int technicalRequestId);

    @SqlQuery("select * from read_report_systems(:technicalRequestId)")
    @RegisterBeanMapper(ReportSystem.class)
    ArrayList<ReportSystem> readReportSystems(@Bind("technicalRequestId") int technicalRequestId);

    @SqlQuery("select * from read_report_employees(:technicalRequestId)")
    @RegisterBeanMapper(ReportEmployee.class)
    ArrayList<ReportEmployee> readReportEmployees(@Bind("technicalRequestId") int technicalRequestId);
}
