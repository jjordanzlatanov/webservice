package com.company.webservice.db;

import com.company.webservice.core.System;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface SystemDao {
    @SqlQuery("select * from create_system(:name, :code, :parentSystemId)")
    @RegisterBeanMapper(System.class)
    System create(@BindBean System system);

    @SqlQuery("select * from read_system(:id, :name, :code, :parentSystemId)")
    @RegisterBeanMapper(System.class)
    List<System> read(@BindBean System system);

    @SqlQuery("select * from read_system_single(:id)")
    @RegisterBeanMapper(System.class)
    System readSingle(@BindBean System system);

    @SqlQuery("select * from update_system(:id, :name, :code, :parentSystemId)")
    @RegisterBeanMapper(System.class)
    System update(@BindBean System system);

    @SqlQuery("select * from delete_system(:id, :name, :code, :parentSystemId)")
    @RegisterBeanMapper(System.class)
    List<System> delete(@BindBean System system);
}
