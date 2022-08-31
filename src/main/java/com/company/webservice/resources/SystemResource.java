package com.company.webservice.resources;

import com.company.webservice.core.System;
import com.company.webservice.db.SystemDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/system")
@Produces(MediaType.APPLICATION_JSON)
public class SystemResource {
    private final SystemDao dao;

    public SystemResource(Jdbi jdbi) {
        dao = jdbi.onDemand(SystemDao.class);
    }

    @POST
    public Response createSystem(@HeaderParam("name") String name, @HeaderParam("code") String code, @HeaderParam("parent_system_id") Integer parent_system_id) {
        dao.create(new System(name, code, parent_system_id));
        return Response.ok().build();
    }

    @GET
    public Response readSystem() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateSystem(@HeaderParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("code") String code, @HeaderParam("parent_system_id") Integer parent_system_id) {
        dao.update(new System(id, name, code, parent_system_id));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteSystem(@HeaderParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("code") String code, @HeaderParam("parent_system_id") Integer parent_system_id) {
        dao.delete(new System(id, name, code, parent_system_id));
        return Response.ok().build();
    }
}
