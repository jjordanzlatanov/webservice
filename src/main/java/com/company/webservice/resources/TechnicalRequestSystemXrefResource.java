package com.company.webservice.resources;

import com.company.webservice.core.TechnicalRequestBlockXref;
import com.company.webservice.core.TechnicalRequestSystemXref;
import com.company.webservice.db.TechnicalRequestSystemXrefDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/technical_request_system")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalRequestSystemXrefResource {
    private final TechnicalRequestSystemXrefDao dao;

    public TechnicalRequestSystemXrefResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestSystemXrefDao.class);
    }

    @POST
    public Response createTechnicalRequestSystemXref(@QueryParam("technical_request_id") Integer technical_request_id, @QueryParam("system_id") Integer system_id) {
        dao.create(new TechnicalRequestSystemXref(technical_request_id, system_id));
        return Response.ok().build();
    }

    @GET
    public Response readTechnicalRequestSystemXref() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateTechnicalRequestSystemXref(@QueryParam("technical_request_id") Integer technical_request_id, @QueryParam("system_id") Integer system_id) {
        dao.update(new TechnicalRequestSystemXref(technical_request_id, system_id));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTechnicalRequestSystemXref(@QueryParam("technical_request_id") Integer technical_request_id, @QueryParam("system_id") Integer system_id) {
        dao.delete(new TechnicalRequestSystemXref(technical_request_id, system_id));
        return Response.ok().build();
    }
}
