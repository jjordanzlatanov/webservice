package com.company.webservice.resources;

import com.company.webservice.core.DTF;
import com.company.webservice.core.TechnicalRequest;
import com.company.webservice.db.TechnicalRequestDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/technical_request")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalRequestResource {
    private final TechnicalRequestDao dao;

    public TechnicalRequestResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalRequestDao.class);
    }

    @POST
    public Response createTechnicalRequest(@QueryParam("name") String name, @QueryParam("description") String description) {
        dao.create(new TechnicalRequest(name, description, DTF.now()));
        return Response.ok().build();
    }

    @GET
    public Response readTechnicalRequest() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateTechnicalRequest(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("creation_time") LocalDateTime creation_time) {
        dao.update(new TechnicalRequest(id, name, description, creation_time));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTechnicalRequest(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("creation_time") LocalDateTime creation_time) {
        dao.delete(new TechnicalRequest(id, name, description, creation_time));
        return Response.ok().build();
    }
}
