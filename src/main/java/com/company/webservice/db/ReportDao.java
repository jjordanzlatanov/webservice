package com.company.webservice.db;

import org.jdbi.v3.sqlobject.SingleValue;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import java.util.ArrayList;

public interface ReportDao {
    @SqlQuery("select read_first_gen_system_ids(?)")
    @SingleValue
    ArrayList<Integer> readFirstGenSystemIds(ArrayList<String> codes);

    @SqlQuery("select read_subsystem_ids(?)")
    @SingleValue
    ArrayList<Integer> readSubsystemIds(ArrayList<Integer> parent_ids);
}
