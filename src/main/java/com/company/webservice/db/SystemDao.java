package com.company.webservice.db;

import com.company.webservice.core.System;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface SystemDao {
    @SqlUpdate("select create_system(:name, :code, :parent_system_id)")
    void create(@BindBean System system);

    @SqlQuery("select * from read_system()")
    @RegisterBeanMapper(System.class)
    List<System> read();

    @SqlUpdate("select update_system(:id, :name, :code, :parent_system_id)")
    void update(@BindBean System system);

    @SqlUpdate("select delete_system(:id, :name, :code, :parent_system_id)")
    void delete(@BindBean System system);
}
