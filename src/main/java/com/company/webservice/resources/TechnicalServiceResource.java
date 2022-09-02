package com.company.webservice.resources;

import com.company.webservice.core.DTF;
import com.company.webservice.core.TechnicalService;
import com.company.webservice.db.TechnicalServiceDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/technical_service")
@Produces(MediaType.APPLICATION_JSON)
public class TechnicalServiceResource {
    private final TechnicalServiceDao dao;

    public TechnicalServiceResource(Jdbi jdbi) {
        dao = jdbi.onDemand(TechnicalServiceDao.class);
    }

    @POST
    public Response createTechnicalService(@HeaderParam("name") String name, @HeaderParam("description") String description) {
        dao.create(new TechnicalService(name, description, DTF.now()));
        return Response.ok().build();
    }

    @GET
    public Response readTechnicalService() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateTechnicalService(@HeaderParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("description") String description, @HeaderParam("creation_time") LocalDateTime creation_time) {
        dao.update(new TechnicalService(id, name, description, creation_time));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTechnicalService(@HeaderParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("description") String description, @HeaderParam("creation_time") LocalDateTime creation_time) {
        dao.delete(new TechnicalService(id, name, description, creation_time));
        return Response.ok().build();
    }
}
