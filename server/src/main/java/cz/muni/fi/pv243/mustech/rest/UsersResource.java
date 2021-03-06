package cz.muni.fi.pv243.mustech.rest;

import com.fasterxml.jackson.annotation.JsonRootName;
import cz.muni.fi.pv243.mustech.model.RoleType;
import cz.muni.fi.pv243.mustech.model.User;
import cz.muni.fi.pv243.mustech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Rest endpoint for User entity
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    @Inject
    private UserService userService;

    @GET
    @RolesAllowed({"admin","user"})
    public Users findAll() {
        return new Users(userService.findAll());
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin","user"})
    public User findById(@PathParam("id") Long id) {
        log.info("Getting: " + id);
        return userService.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public void register(User user) {
        user.setRole(RoleType.USER);
        userService.saveOrUpdate(user);
    }

    @JsonRootName("users")
    class Users extends ArrayList<User> {
        public Users(Collection<User> users) {
            addAll(users);
        }
    }
}
