package com.company.webservice.db;

import com.company.webservice.core.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserDao {
    @SqlQuery("select * from create_user(:username, :email, :password)")
    @RegisterBeanMapper(User.class)
    User create(@BindBean User user);

    @SqlQuery("select * from read_user(:id, :username, :email, :password)")
    @RegisterBeanMapper(User.class)
    List<User> read(@BindBean User user);

    @SqlQuery("select * from read_user_single(:id)")
    @RegisterBeanMapper(User.class)
    User readSingle(@BindBean User user);

    @SqlQuery("select * from update_user(:id, :username, :email, :password)")
    @RegisterBeanMapper(User.class)
    User update(@BindBean User user);

    @SqlQuery("select * from delete_user(:id, :username, :email, :password)")
    @RegisterBeanMapper(User.class)
    List<User> delete(@BindBean User user);

    @SqlUpdate("select create_salt(:userId, :salt)")
    void createSalt(@Bind("userId") int userId, @Bind("salt") String salt);

    @SqlQuery("select check_salt(:salt)")
    boolean checkSalt(@Bind("salt") String salt);

    @SqlQuery("select get_salt(:username)")
    String getSalt(@Bind("username") String username);

    @SqlQuery("select check_hash(:hash)")
    boolean checkHash(@Bind("hash") String hash);
}
