package com.company.webservice.resources;

import com.company.webservice.core.System;
import com.company.webservice.db.SystemDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/system")
@Produces(MediaType.APPLICATION_JSON)
public class SystemResource {
    private final SystemDao dao;

    public SystemResource(Jdbi jdbi) {
        dao = jdbi.onDemand(SystemDao.class);
    }

    @POST
    public Response createSystem(@QueryParam("name") String name, @QueryParam("code") String code, @QueryParam("parent_system_id") int parent_system_id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.create(new System(name, code, parent_system_id)), "null")).build();
    }

    @GET
    public Response readSystem() {
        return Response.ok().entity(dao.read()).build();
    }

    @GET
    @Path("/{id}")
    public Response readSystemSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new System(id)), "null")).build();
    }

    @PUT
    public Response updateSystem(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("code") String code, @QueryParam("parent_system_id") int parent_system_id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new System(id, name, code, parent_system_id)), "null")).build();
    }

    @DELETE
    public Response deleteSystem(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("code") String code, @QueryParam("parent_system_id") int parent_system_id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.delete(new System(id, name, code, parent_system_id)), "null")).build();
    }
}
