package com.company.webservice.resources;

import com.company.webservice.core.Block;
import com.company.webservice.db.BlockDao;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/block")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class BlockResource {
    private final BlockDao dao;

    public BlockResource(Jdbi jdbi) {
        dao = jdbi.onDemand(BlockDao.class);
    }

    @POST
    public Response createBlock(@QueryParam("name") String name, @QueryParam("code") String code) {
        return Response.ok().entity(dao.create(new Block(name, code))).build();
    }

    @GET
    public Response readBlock(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("code") String code) {
        return Response.ok().entity(dao.read(new Block(id, name, code))).build();
    }

    @GET
    @Path("/{id}")
    public Response readBlockSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new Block(id)), "null")).build();
    }

    @PUT
    public Response updateBlock(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("code") String code) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new Block(id, name, code)), "null")).build();
    }

    @DELETE
    public Response deleteBlock(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("code") String code) {
        return Response.ok().entity(dao.delete(new Block(id, name, code))).build();
    }
}
