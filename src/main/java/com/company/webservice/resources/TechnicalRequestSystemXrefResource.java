package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestSystemXref;
import com.company.webservice.db.TechnicalRequestSystemXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/technical_request_system")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class TechnicalRequestSystemXrefResource {
    private final TechnicalRequestSystemXrefDao dao;

    public TechnicalRequestSystemXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestSystemXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestSystemXref(@QueryParam("technical_request_id") int technicalRequestId, @QueryParam("system_id") int systemId) {
        return Response.ok().entity(dao.create(new TechnicalRequestSystemXref(technicalRequestId, systemId))).build();
    }

    @GET
    public Response readTechnicalRequestSystemXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technicalRequestId, @QueryParam("system_id") int systemId) {
        return Response.ok().entity(dao.read(new TechnicalRequestSystemXref(id, technicalRequestId, systemId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readTechnicalRequestSystemXref(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new TechnicalRequestSystemXref(id)), "null")).build();
    }

    @PUT
    public Response updateTechnicalRequestSystemXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technicalRequestId, @QueryParam("system_id") int systemId) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new TechnicalRequestSystemXref(id, technicalRequestId, systemId)), "null")).build();
    }

    @DELETE
    public Response deleteTechnicalRequestSystemXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technicalRequestId, @QueryParam("system_id") int systemId) {
        return Response.ok().entity(dao.delete(new TechnicalRequestSystemXref(id, technicalRequestId, systemId))).build();
    }
}
