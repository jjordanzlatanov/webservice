package com.company.webservice.resources;

import com.company.webservice.core.Hasher;
import com.company.webservice.core.User;
import com.company.webservice.db.UserDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDao dao;

    public UserResource(Jdbi jdbi) {
        dao = jdbi.onDemand(UserDao.class);
    }

    @POST
    public Response createUser(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) throws NoSuchAlgorithmException {
        String salt = Hasher.generateSalt();

        while(dao.checkSalt(salt)) {
            salt = Hasher.generateSalt();
        }

        String passwordHash = Hasher.hash(password, salt);

        User user = dao.create(new User(username, email, passwordHash));
        dao.createSalt(user.getId(), salt);

        return Response.ok().entity(user).build();
    }

    @GET
    public Response readUser(@QueryParam("id") int id, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok().entity(dao.read(new User(id, username, email, password))).build();
    }

    @GET
    @Path("/{id}")
    public Response readUserSingle(@PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new User(id)), "null")).build();
    }

    @PUT
    public Response updateUser(@QueryParam("id") int id, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new User(id, username, email, password)), "null")).build();
    }

    @DELETE
    public Response deleteUser(@QueryParam("id") int id, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok().entity(dao.delete(new User(id, username, email, password))).build();
    }
}
