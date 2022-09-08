package com.company.webservice.resources;

import com.company.webservice.core.Activity;
import com.company.webservice.db.ActivityDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Objects;

@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityResource {
    private final ActivityDao dao;

    public ActivityResource(Jdbi jdbi) {
        dao = jdbi.onDemand(ActivityDao.class);
    }

    @POST
    public Response createActivity(@QueryParam("name") String name) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.create(new Activity(name)), "null")).build();
    }

    @GET
    public Response readActivity() {
        return Response.ok().entity(dao.read()).build();
    }

    @GET
    @Path("/{id}")
    public Response readActivitySingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new Activity(id)), "null")).build();
    }

    @PUT
    public Response updateActivity(@QueryParam("id") int id, @QueryParam("name") String name) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new Activity(id, name)), "null")).build();
    }

    @DELETE
    public Response deleteActivity(@QueryParam("id") int id, @QueryParam("name") String name) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.delete(new Activity(id, name)), "null")).build();
    }

    @Path("/create_standard")
    @POST
    public Response createActivityStandard() {
        ArrayList<Object> activities = new ArrayList<>();
        activities.add(Objects.requireNonNullElse(dao.create(new Activity("creation")), "null"));
        activities.add(Objects.requireNonNullElse(dao.create(new Activity("confirmation")), "null"));
        activities.add(Objects.requireNonNullElse(dao.create(new Activity("approval")), "null"));
        activities.add(Objects.requireNonNullElse(dao.create(new Activity("verification")), "null"));
        return Response.ok().entity(activities).build();
    }
}
