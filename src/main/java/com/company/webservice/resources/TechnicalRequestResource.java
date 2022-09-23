package com.company.webservice.resources;

import com.company.webservice.core.DTF;
import com.company.webservice.core.TechnicalRequest;
import com.company.webservice.db.TechnicalRequestDao;
import org.jdbi.v3.core.Jdbi;

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
        if(!employeeName.isEmpty()) {
            String employeeFirstName = employeeName.substring(0, employeeName.indexOf(" "));
            String employeeLastName = employeeName.substring(employeeName.indexOf(" ") + 1);
        }

        if(!blockCodesPar.isEmpty()) {
            ArrayList<String> block_codes = new ArrayList<>(Arrays.asList(blockCodesPar.split(",")));
            ArrayList<Integer> blockIds = dao.readBlockIds(block_codes);
        }

        if(!systemCodesPar.isEmpty()) {
            ArrayList<String> system_codes = new ArrayList<>(Arrays.asList(systemCodesPar.split(",")));
            ArrayList<Integer> systemIds = dao.readSystemIds(system_codes);
        }

        return Response.ok().build();
    }
}
