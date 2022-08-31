package com.company.webservice.resources;

import com.company.webservice.core.Block;
import com.company.webservice.db.BlockDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/block")
@Produces(MediaType.APPLICATION_JSON)
public class BlockResource {
    private final BlockDao dao;

    public BlockResource(Jdbi jdbi) {
        dao = jdbi.onDemand(BlockDao.class);
    }

    @POST
    public Response createBlock(@HeaderParam("name") String name, @HeaderParam("code") String code) {
        dao.create(new Block(name, code));
        return Response.ok().build();
    }

    @GET
    public Response readBlock() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateBlock(@HeaderParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("code") String code) {
        dao.update(new Block(id, name, code));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteBlock(@HeaderParam("id") Integer id, @HeaderParam("name") String name, @HeaderParam("code") String code) {
        dao.delete(new Block(id, name, code));
        return Response.ok().build();
    }
}
