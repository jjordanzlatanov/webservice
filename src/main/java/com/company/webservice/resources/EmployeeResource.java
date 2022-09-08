package com.company.webservice.resources;

import com.company.webservice.core.Employee;
import com.company.webservice.db.EmployeeDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    private final EmployeeDao dao;

    public EmployeeResource(Jdbi jdbi) {
        dao = jdbi.onDemand(EmployeeDao.class);
    }

    @POST
    public Response createEmployee(@QueryParam("first_name") String first_name, @QueryParam("surname") String surname, @QueryParam("last_name") String last_name, @QueryParam("pin") int pin) {
        return Response.ok().entity(dao.create(new Employee(first_name, surname, last_name, pin))).build();
    }

    @GET
    public Response readEmployee(@QueryParam("id") int id, @QueryParam("first_name") String first_name, @QueryParam("surname") String surname, @QueryParam("last_name") String last_name, @QueryParam("pin") int pin) {
        return Response.ok(dao.read(new Employee(id, first_name, surname, last_name, pin))).build();
    }

    @GET
    @Path("/{id}")
    public Response readEmployeeSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new Employee(id)), "null")).build();
    }

    @PUT
    public Response updateEmployee(@QueryParam("id") int id, @QueryParam("first_name") String first_name, @QueryParam("surname") String surname, @QueryParam("last_name") String last_name, @QueryParam("pin") int pin) {
        if((!first_name.equals("") || !surname.equals("") || !last_name.equals("")) && (dao.checkEmployeeInTechnicalRequest(new Employee(id)))) {
            return Response.ok().entity("Cannot change the employee's name while a technical request he's on is in progress").build();
        }

        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new Employee(id, first_name, surname, last_name, pin)), "null")).build();
    }

    @DELETE
    public Response deleteEmployee(@QueryParam("id") int id, @QueryParam("first_name") String first_name, @QueryParam("surname") String surname, @QueryParam("last_name") String last_name, @QueryParam("pin") int pin) {
        return Response.ok().entity(dao.delete(new Employee(id, first_name, surname, last_name, pin))).build();
    }
}
