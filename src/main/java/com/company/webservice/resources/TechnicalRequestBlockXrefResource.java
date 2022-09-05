package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestBlockXref;
import com.company.webservice.db.TechnicalRequestBlockXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/technical_request_block")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalRequestBlockXrefResource {
    private final TechnicalRequestBlockXrefDao dao;

    public TechnicalRequestBlockXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestBlockXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestBlockXref(@QueryParam("technical_request_id") Integer technical_request_id, @QueryParam("block_id") Integer block_id) {
        dao.create(new TechnicalRequestBlockXref(technical_request_id, block_id));
        return Response.ok().build();
    }

    @GET
    public Response readTechnicalRequestBlockXref() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateTechnicalRequestBlockXref(@QueryParam("technical_request_id") Integer technical_request_id, @QueryParam("block_id") Integer block_id) {
        dao.update(new TechnicalRequestBlockXref(technical_request_id, block_id));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTechnicalRequestBlockXref(@QueryParam("technical_request_id") Integer technical_request_id, @QueryParam("block_id") Integer block_id) {
        dao.delete(new TechnicalRequestBlockXref(technical_request_id, block_id));
        return Response.ok().build();
    }
}