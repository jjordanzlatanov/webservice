package com.company.webservice.db;

import com.company.webservice.core.Block;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BlockDao {
    @SqlQuery("select * from create_block(:name, :code)")
    @RegisterBeanMapper(Block.class)
    Block create(@BindBean Block block);

    @SqlQuery("select * from read_block(:id, :name, :code)")
    @RegisterBeanMapper(Block.class)
    List<Block> read(@BindBean Block block);

    @SqlQuery("select * from read_block_single(:id)")
    @RegisterBeanMapper(Block.class)
    Block readSingle(@BindBean Block block);

    @SqlQuery("select * from update_block(:id, :name, :code)")
    @RegisterBeanMapper(Block.class)
    Block update(@BindBean Block block);

    @SqlQuery("select * from delete_block(:id, :name, :code)")
    @RegisterBeanMapper(Block.class)
    List<Block> delete(@BindBean Block block);
}
