package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestBlockXref;
import com.company.webservice.db.TechnicalRequestBlockXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/technical_request_block")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class TechnicalRequestBlockXrefResource {
    private final TechnicalRequestBlockXrefDao dao;

    public TechnicalRequestBlockXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestBlockXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestBlockXref(@QueryParam("technical_request_id") int technicalRequestId, @QueryParam("block_id") int blockId) {
        return Response.ok().entity(dao.create(new TechnicalRequestBlockXref(technicalRequestId, blockId))).build();
    }

    @GET
    public Response readTechnicalRequestBlockXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technicalRequestId, @QueryParam("block_id") int blockId) {
        return Response.ok().entity(dao.read(new TechnicalRequestBlockXref(id, technicalRequestId, blockId))).build();
    }

    @GET
    @Path("/{id}")
    public Response readTechnicalRequestBlockXrefSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new TechnicalRequestBlockXref(id)), "null")).build();
    }

    @PUT
    public Response updateTechnicalRequestBlockXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technicalRequestId, @QueryParam("block_id") int blockId) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new TechnicalRequestBlockXref(id, technicalRequestId, blockId)), "null")).build();
    }

    @DELETE
    public Response deleteTechnicalRequestBlockXref(@QueryParam("id") int id, @QueryParam("technical_request_id") int technicalRequestId, @QueryParam("block_id") int blockId) {
        return Response.ok().entity(dao.delete(new TechnicalRequestBlockXref(id, technicalRequestId, blockId))).build();
    }
}
