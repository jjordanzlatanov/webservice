package com.company.webservice.resources;

import com.company.webservice.db.BlockDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/receive")
@Produces(MediaType.APPLICATION_JSON)
public class BlockResource {
    private final Jdbi jdbi;
    private final BlockDao dao;

    public BlockResource(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.dao = jdbi.onDemand(BlockDao.class);
    }

    @GET
    public Response readblock() {
        return Response.ok().entity(dao.ReadBlock()).build();
    }
}
