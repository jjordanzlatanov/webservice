package com.company.webservice.db;

import com.company.webservice.core.Activity;
import com.company.webservice.core.Block;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ActivityDao {
    @SqlQuery("select * from create_activity(:name)")
    @RegisterBeanMapper(Block.class)
    Activity create(@BindBean Activity activity);

    @SqlQuery("select * from read_activity(:id, :name)")
    @RegisterBeanMapper(Activity.class)
    List<Activity> read(@BindBean Activity activity);

    @SqlQuery("select * from read_activity_single(:id)")
    @RegisterBeanMapper(Activity.class)
    Activity readSingle(@BindBean Activity activity);

    @SqlQuery("select * from update_activity(:id, :name)")
    @RegisterBeanMapper(Block.class)
    Activity update(@BindBean Activity activity);

    @SqlQuery("select * from delete_activity(:id, :name)")
    @RegisterBeanMapper(Block.class)
    Activity delete(@BindBean Activity activity);
}
