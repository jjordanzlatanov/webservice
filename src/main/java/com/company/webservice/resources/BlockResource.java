package com.company.webservice.resources;

import com.company.webservice.core.Block;
import com.company.webservice.db.BlockDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/receive")
@Produces(MediaType.APPLICATION_JSON)
public class BlockResource {
    private final BlockDao dao;

    public BlockResource(Jdbi jdbi) {
        dao = jdbi.onDemand(BlockDao.class);
    }

    @GET
    public Response readblock() {
        dao.InsertBlock("fff", "fff");
        List<Block> blockList =  dao.ListBlock();
        System.out.println(blockList.get(0).getName());
        return Response.ok().build();
    }
}
