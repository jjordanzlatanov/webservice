package com.company.webservice.db;

import com.company.webservice.core.Block;
import com.company.webservice.core.TechnicalRequest;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface ReportDao {
    @SqlQuery("select * from block limit 1")
    @RegisterBeanMapper(Block.class)
    Block read();
}
