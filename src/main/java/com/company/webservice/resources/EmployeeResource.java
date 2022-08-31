package com.company.webservice.resources;

import com.company.webservice.core.Employee;
import com.company.webservice.db.EmployeeDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    private final EmployeeDao dao;

    public EmployeeResource(Jdbi jdbi) {
        dao = jdbi.onDemand(EmployeeDao.class);
    }

    @POST
    public Response createSystem(@HeaderParam("first_name") String first_name, @HeaderParam("surname") String surname, @HeaderParam("last_name") String last_name, @HeaderParam("pin") Integer pin) {
        dao.create(new Employee(first_name, surname, last_name, pin));
        return Response.ok().build();
    }

    @GET
    public Response readSystem() {
        return Response.ok(dao.read()).build();
    }

    @PUT
    public Response updateSystem(@HeaderParam("id") Integer id, @HeaderParam("first_name") String first_name, @HeaderParam("surname") String surname, @HeaderParam("last_name") String last_name, @HeaderParam("pin") Integer pin) {
        dao.update(new Employee(id, first_name, surname, last_name, pin));
        return Response.ok().build();
    }

    @DELETE
    public Response deleteSystem(@HeaderParam("id") Integer id, @HeaderParam("first_name") String first_name, @HeaderParam("surname") String surname, @HeaderParam("last_name") String last_name, @HeaderParam("pin") Integer pin) {
        dao.delete(new Employee(id, first_name, surname, last_name, pin));
        return Response.ok().build();
    }
}
