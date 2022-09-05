package com.company.webservice.resources;

import com.company.webservice.core.Activity;
import com.company.webservice.db.ActivityDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityResource {
    private final ActivityDao dao;

    public ActivityResource(Jdbi jdbi) {
        dao = jdbi.onDemand(ActivityDao.class);
    }

    @POST
    public Response createActivity(@QueryParam("name") String name) {
        dao.create(new Activity(name));
        return Response.ok().build();
    }

    @GET
    public Response readActivity() {
        return Response.ok().entity(dao.read()).build();
    }

    @PUT
    public Response updateActivity(@QueryParam("id") Integer id, @QueryParam("name") String name) {
        dao.update(new Activity(id, name));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteActivity(@QueryParam("id") Integer id, @QueryParam("name") String name) {
        dao.delete(new Activity(id, name));
        return Response.ok().build();
    }

    @Path("/create_standard")
    @POST
    public Response createActivityStandard() {
        dao.create(new Activity("creation"));
        dao.create(new Activity("confirmation"));
        dao.create(new Activity("approval"));
        dao.create(new Activity("verification"));

        return Response.ok().build();
    }
}
