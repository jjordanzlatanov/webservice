package com.company.webservice.db;

import com.company.webservice.core.Employee;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface EmployeeDao {
    @SqlQuery("select * from create_employee(:firstName, :surname, :lastName, :pin)")
    @RegisterBeanMapper(Employee.class)
    Employee create(@BindBean Employee employee);

    @SqlQuery("select * from read_employee(:id, :firstName, :surname, :lastName, :pin)")
    @RegisterBeanMapper(Employee.class)
    List<Employee> read(@BindBean Employee employee);

    @SqlQuery("select * from read_employee_single(:id)")
    @RegisterBeanMapper(Employee.class)
    Employee readSingle(@BindBean Employee employee);

    @SqlQuery("select * from update_employee(:id, :firstName, :surname, :lastName, :pin)")
    @RegisterBeanMapper(Employee.class)
    Employee update(@BindBean Employee employee);

    @SqlQuery("select * from delete_employee(:id, :firstName, :surname, :lastName, :pin)")
    @RegisterBeanMapper(Employee.class)
    List<Employee> delete(@BindBean Employee employee);

    @SqlQuery("select check_employee_in_technical_request(:id)")
    @RegisterBeanMapper(Employee.class)
    boolean checkEmployeeInTechnicalRequest(@BindBean Employee employee);
}
