package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestActivityXref;
import com.company.webservice.db.TechnicalRequestActivityXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/technical_request_activity")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalRequestActivityXrefResource {
    private final TechnicalRequestActivityXrefDao dao;

    public TechnicalRequestActivityXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestActivityXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestActivityXref(@QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        return Response.ok().entity(dao.create(new TechnicalRequestActivityXref(technical_request_id, activity_id, employee_id))).build();
    }

    @GET
    public Response readTechnicalRequestActivityXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        return Response.ok().entity(dao.read(new TechnicalRequestActivityXref(id, technical_request_id, activity_id, employee_id))).build();
    }

    @GET
    @Path("/{id}")
    public Response readTechnicalRequestActivityXrefSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new TechnicalRequestActivityXref(id)), "null")).build();
    }

    @PUT
    public Response updateTechnicalRequestActivityXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new TechnicalRequestActivityXref(id, technical_request_id, activity_id, employee_id)), "null")).build();
    }

    @DELETE
    public Response deleteTechnicalRequestActivityXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        return Response.ok().entity(dao.delete(new TechnicalRequestActivityXref(id, technical_request_id, activity_id, employee_id))).build();
    }
}
