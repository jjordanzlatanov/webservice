package com.company.webservice.db;

import com.company.webservice.core.Block;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BlockDao {
    @SqlUpdate("select create_block(:name, :code)")
    void create(@BindBean Block block);

    @SqlQuery("select * from read_block()")
    @RegisterBeanMapper(Block.class)
    List<Block> read();

    @SqlUpdate("select update_block(:id, :name, :code)")
    void update(@BindBean Block block);

    @SqlUpdate("select delete_block(:id, :name, :code)")
    void delete(@BindBean Block block);
}
