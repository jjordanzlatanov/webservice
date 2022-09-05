package com.company.webservice.db;

import com.company.webservice.core.Activity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ActivityDao {
    @SqlUpdate("select create_activity(:name)")
    void create(@BindBean Activity activity);

    @SqlQuery("select * from read_activity()")
    @RegisterBeanMapper(Activity.class)
    List<Activity> read();

    @SqlUpdate("select update_activity(:id, :name)")
    void update(@BindBean Activity activity);

    @SqlUpdate("select delete_activity(:id, :name)")
    void delete(@BindBean Activity activity);
}
