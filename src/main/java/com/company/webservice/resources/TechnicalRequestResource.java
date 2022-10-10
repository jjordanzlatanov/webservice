package com.company.webservice.resources;

import com.company.webservice.core.DTF;
import com.company.webservice.core.ReportRequest;
import com.company.webservice.core.TechnicalRequest;
import com.company.webservice.db.TechnicalRequestDao;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Path("/technical_request")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class TechnicalRequestResource {
    private final TechnicalRequestDao dao;

    public TechnicalRequestResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestDao.class);
    }

    @POST
    public Response createTechnicalRequest(@QueryParam("name") String name, @QueryParam("description") String description) {
        return Response.ok().entity(dao.create(new TechnicalRequest(name, description, DTF.now()))).build();
    }

    @GET
    public Response readTechnicalRequest(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("creation_time") LocalDateTime creationTime) {
        return Response.ok().entity(dao.read(new TechnicalRequest(id, name, description, creationTime))).build();
    }

    @GET
    @Path("/{id}")
    public Response readTechnicalRequestSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new TechnicalRequest(id)), "null")).build();
    }

    @PUT
    public Response updateTechnicalRequest(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("creation_time") LocalDateTime creationTime) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new TechnicalRequest(id, name, description, creationTime)), "null")).build();
    }

    @DELETE
    public Response deleteTechnicalRequest(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("creation_time") LocalDateTime creationTime) {
        return Response.ok().entity(dao.delete(new TechnicalRequest(id, name, description, creationTime))).build();
    }

    @GET
    @Path("/report")
    public Response readReport(@QueryParam("employee_name") String employeeName, @QueryParam("block_codes") String blockCodesPar, @QueryParam("system_codes") String systemCodesPar, @QueryParam("creation_date") LocalDate creationDate) {
        String query = "select * from technical_request where true is true";

        if(!employeeName.isEmpty()) {
            String employeeFirstName = employeeName.substring(0, employeeName.indexOf(" "));
            String employeeLastName = employeeName.substring(employeeName.indexOf(" ") + 1);

            query += " and id in (select technical_request_id from technical_request_activity_xref " +
                    "where employee_id in (select id from employee where first_name = '" + employeeFirstName  + "' and last_name = '" + employeeLastName + "'))";
        }

        if(!blockCodesPar.isEmpty()) {
            String blockCodes = blockCodesPar.replaceAll(",", "','");

            query += " and id in (select technical_request_id from technical_request_block_xref where block_id in (select id from block where code in ('" + blockCodes + "')))";
        }

        if(!systemCodesPar.isEmpty()) {
            ArrayList<String> system_codes = new ArrayList<>(Arrays.asList(systemCodesPar.split(",")));
            ArrayList<Integer> systemIds = dao.readSystemChildIds(system_codes);

            String systemIdsStr = systemIds.toString();
            systemIdsStr = systemIdsStr.replaceAll(" ", "");
            systemIdsStr = systemIdsStr.substring(1, systemIdsStr.length() - 1);

            query += " and id in (select technical_request_id from technical_request_system_xref where system_id in (" + systemIdsStr + "))";
        }

        if(creationDate != null) {
            query += " and creation_time::date = '" + creationDate + "'";
        }

        ArrayList<TechnicalRequest> technicalRequests = dao.readReportTechnicalRequest(query);
        ArrayList<ReportRequest> reportRequests = new ArrayList<>();

        for(TechnicalRequest technicalRequest : technicalRequests) {
            reportRequests.add(new ReportRequest(technicalRequest.getName(), technicalRequest.getDescription(), technicalRequest.getCreationTime(), dao.readBlockCodes(technicalRequest.getId()), dao.readReportSystems(technicalRequest.getId()), dao.readReportEmployees(technicalRequest.getId())));
        }

        return Response.ok().entity(reportRequests).build();
    }
}
