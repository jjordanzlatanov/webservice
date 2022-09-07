package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestActivityXref;
import com.company.webservice.db.TechnicalRequestActivityXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/technical_request_activity")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalRequestActivityXrefResource {
    private final TechnicalRequestActivityXrefDao dao;

    public TechnicalRequestActivityXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestActivityXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestActivityXref(@QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        dao.create(new TechnicalRequestActivityXref(technical_request_id, activity_id, employee_id));
        return Response.ok().build();
    }

    @GET
    public Response readTechnicalRequestActivityXref() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateTechnicalRequestActivityXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        dao.update(new TechnicalRequestActivityXref(id, technical_request_id, activity_id, employee_id));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTechnicalRequestActivityXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("activity_id") int activity_id, @QueryParam("employee_id") int employee_id) {
        dao.delete(new TechnicalRequestActivityXref(id, technical_request_id, activity_id, employee_id));
        return Response.ok().build();
    }
}
