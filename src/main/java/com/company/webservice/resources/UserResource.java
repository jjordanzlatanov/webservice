package com.company.webservice.resources;

import com.company.webservice.core.AuthI;
import com.company.webservice.core.Token;
import com.company.webservice.core.User;
import com.company.webservice.db.UserDao;
import io.dropwizard.auth.Auth;
import org.eclipse.jetty.http.HttpStatus;
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
        String salt = AuthI.generateSalt();

        while(dao.checkSalt(salt)) {
            salt = AuthI.generateSalt();
        }

        String passwordHash = AuthI.hash(salt, password);

        User user = dao.create(new User(username, email, passwordHash));
        dao.createSalt(user.getId(), salt);

        return Response.ok().entity(user).build();
    }

    @GET
    public Response readUser(@Auth Token token, @QueryParam("id") int id, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok().entity(dao.read(new User(id, username, email, password))).build();
    }

    @GET
    @Path("/{id}")
    public Response readUserSingle(@Auth Token token, @PathParam("id") int id) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.readSingle(new User(id)), "null")).build();
    }

    @PUT
    public Response updateUser(@Auth Token token, @QueryParam("id") int id, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok().entity(Objects.requireNonNullElse(dao.update(new User(id, username, email, password)), "null")).build();
    }

    @DELETE
    public Response deleteUser(@Auth Token token, @QueryParam("id") int id, @QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        return Response.ok().entity(dao.delete(new User(id, username, email, password))).build();
    }

    @GET
    @Path("/authenticate")
    public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) throws NoSuchAlgorithmException {
        String salt = dao.getSalt(username);

        if(salt == null) {
            return Response.status(HttpStatus.UNAUTHORIZED_401).build();
        }

        String hash = AuthI.hash(salt, password);

        if(dao.checkHash(hash)) {
            return Response.ok().entity(AuthI.generateToken(username, hash)).build();
        }

        return Response.status(HttpStatus.UNAUTHORIZED_401).build();
    }
}
