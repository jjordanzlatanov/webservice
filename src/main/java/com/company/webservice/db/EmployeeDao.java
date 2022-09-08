package com.company.webservice.db;

import com.company.webservice.core.Employee;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface EmployeeDao {
    @SqlUpdate("select create_employee(:first_name, :surname, :last_name, :pin)")
    void create(@BindBean Employee employee);

    @SqlQuery("select * from read_employee()")
    @RegisterBeanMapper(Employee.class)
    List<Employee> read();

    @SqlQuery("select * from read_employee_single(:id)")
    @RegisterBeanMapper(Employee.class)
    Employee readSingle(@BindBean Employee employee);

    @SqlUpdate("select update_employee(:id, :first_name, :surname, :last_name, :pin)")
    void update(@BindBean Employee employee);

    @SqlUpdate("select delete_employee(:id, :first_name, :surname, :last_name, :pin)")
    void delete(@BindBean Employee employee);
}
