package com.company.webservice.resources;

import com.company.webservice.db.ReportDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {
    private final ReportDao dao;

    public ReportResource(Jdbi jdbi) {
        dao = jdbi.onDemand(ReportDao.class);
    }

    @GET
    public Response read(@QueryParam("employee_name") String employeeName, @QueryParam("block_codes") String blockCodesPar, @QueryParam("system_codes") String systemCodesPar, @QueryParam("creation_date") LocalDate creationDate) {
        ArrayList<String> block_codes = new ArrayList<>(Arrays.asList(blockCodesPar.split(",")));
        ArrayList<String> system_codes = new ArrayList<>(Arrays.asList(systemCodesPar.split(",")));

        String employeeFirstName = employeeName.substring(0, employeeName.indexOf(" "));
        String employeeLastName = employeeName.substring(employeeName.indexOf(" ") + 1);
        String creationDateText = creationDate.toString();

        if(blockCodesPar.equals("")) {
            block_codes.clear();
        }

        if(systemCodesPar.equals("")) {
            system_codes.clear();
        }

        ArrayList<Integer> firstGenSystemIds = dao.readFirstGenSystemIds(system_codes);
        ArrayList<Integer> systemIds = dao.readSubsystemIds(firstGenSystemIds);

        return Response.ok().entity(systemIds).build();
    }
}
