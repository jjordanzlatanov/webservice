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
    public Response read(@QueryParam("employee_name") String employee_name, @QueryParam("block_codes") String block_codes_par, @QueryParam("system_codes") String system_codes_par, @QueryParam("creation_date") LocalDate creation_date) {
        ArrayList<String> block_codes = new ArrayList<>(Arrays.asList(block_codes_par.split(",")));
        ArrayList<String> system_codes = new ArrayList<>(Arrays.asList(system_codes_par.split(",")));
        String employee_first_name = employee_name.substring(0, employee_name.indexOf(" "));
        String employee_last_name = employee_name.substring(employee_name.indexOf(" ") + 1);
        String creation_date_text = creation_date.toString();

        if(block_codes_par.equals("")) {
            block_codes.clear();
        }

        return Response.ok().entity(creation_date_text).build();
    }
}
