package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestBlockXref;
import com.company.webservice.core.TechnicalRequestSystemXref;
import com.company.webservice.db.TechnicalRequestSystemXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/technical_request_system")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalRequestSystemXrefResource {
    private final TechnicalRequestSystemXrefDao dao;

    public TechnicalRequestSystemXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestSystemXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestSystemXref(@QueryParam("technical_request_id") int technical_request_id, @QueryParam("system_id") int system_id) {
        return Response.ok().entity(dao.create(new TechnicalRequestSystemXref(technical_request_id, system_id))).build();
    }

    @GET
    public Response readTechnicalRequestSystemXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("system_id") int system_id) {
        return Response.ok().entity(dao.read(new TechnicalRequestSystemXref(id, technical_request_id, system_id))).build();
    }

    @GET
    @Path("/{id}")
    public Response readTechnicalRequestSystemXref(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new TechnicalRequestSystemXref(id)), "null")).build();
    }

    @PUT
    public Response updateTechnicalRequestSystemXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("system_id") int system_id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new TechnicalRequestSystemXref(id, technical_request_id, system_id)), "null")).build();
    }

    @DELETE
    public Response deleteTechnicalRequestSystemXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technical_request_id, @QueryParam("system_id") int system_id) {
        return Response.ok().entity(dao.delete(new TechnicalRequestSystemXref(id, technical_request_id, system_id))).build();
    }
}
