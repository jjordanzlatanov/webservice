package com.company.webservice.db;

import com.company.webservice.core.Block;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BlockDao {
    @SqlUpdate("select create_block(:name, :code)")
    void InsertBlock(@Bind("name") String name, @Bind("code") String code);

    @SqlQuery("select * from block")
    @RegisterBeanMapper(Block.class)
    List<Block> ListBlock();

    @SqlUpdate("select update_block(:id, :name, :code)")
    void UpdateBlock(@Bind("id") int id, @Bind("name") String name, @Bind("code") String code);

    @SqlUpdate("select delete_block(:id, :name, :code)")
    void DeleteBlock(@Bind("id") int id, @Bind("name") String name, @Bind("code") String code);
}
