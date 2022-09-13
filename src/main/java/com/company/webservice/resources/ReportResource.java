package com.company.webservice.resources;

import com.company.webservice.db.ReportDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {
    private final ReportDao dao;

    public ReportResource(Jdbi jdbi) {
        dao = jdbi.onDemand(ReportDao.class);
    }

    @GET
    public Response read(@QueryParam("block_codes") String block_codes_par) {
        ArrayList<String> block_codes = new ArrayList<>(Arrays.asList(block_codes_par.split(",")));

        if(block_codes_par.equals("")) {
            block_codes.clear();
        }

        return Response.ok().entity(block_codes).build();
    }
}
