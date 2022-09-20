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
    public Response createEmployee(@QueryParam("first_name") String firstName, @QueryParam("surname") String surname, @QueryParam("last_name") String lastName, @QueryParam("pin") int pin) {
        return Response.ok().entity(dao.create(new Employee(firstName, surname, lastName, pin))).build();
    }

    @GET
    public Response readEmployee(@QueryParam("id") int id, @QueryParam("first_name") String firstName, @QueryParam("surname") String surname, @QueryParam("last_name") String lastName, @QueryParam("pin") int pin) {
        return Response.ok(dao.read(new Employee(id, firstName, surname, lastName, pin))).build();
    }

    @GET
    @Path("/{id}")
    public Response readEmployeeSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new Employee(id)), "null")).build();
    }

    @PUT
    public Response updateEmployee(@QueryParam("id") int id, @QueryParam("first_name") String firstName, @QueryParam("surname") String surname, @QueryParam("last_name") String lastName, @QueryParam("pin") int pin) {
        if((!firstName.equals("") || !surname.equals("") || !lastName.equals("")) && (dao.checkEmployeeInTechnicalRequest(new Employee(id)))) {
            return Response.ok().entity("Cannot change the employee's name while a technical request he's on is in progress").build();
        }

        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new Employee(id, firstName, surname, lastName, pin)), "null")).build();
    }

    @DELETE
    public Response deleteEmployee(@QueryParam("id") int id, @QueryParam("first_name") String firstName, @QueryParam("surname") String surname, @QueryParam("last_name") String lastName, @QueryParam("pin") int pin) {
        return Response.ok().entity(dao.delete(new Employee(id, firstName, surname, lastName, pin))).build();
    }
}
