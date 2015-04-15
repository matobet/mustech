package cz.muni.fi.pv243.mustech.rest;

import com.fasterxml.jackson.annotation.JsonRootName;
import cz.muni.fi.pv243.mustech.User;
import cz.muni.fi.pv243.mustech.dal.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("/users")
@Produces(JsonApi.MIME_TYPE)
public class UsersResource {

    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    @Inject
    private UserRepository userRepository;

    @GET
    public Users get() {
        return new Users(userRepository.findAll());
    }

    @GET
    @Path("/{name}")
    public User getOne(@PathParam("name") String name) {
        log.info("Getting: " + name);
        return userRepository.findByName(name);
    }

    @JsonRootName("users")
    class Users extends ArrayList<User> {
        public Users(Collection<User> users) {
            addAll(users);
        }
    }
}
