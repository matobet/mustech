package cz.muni.fi.pv243.mustech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Hello {

    private final Logger log = LoggerFactory.getLogger(Hello.class);

    @Inject
    private UserRepository userRepository;

    @GET
    public List<User> get() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{name}")
    public User getOne(@PathParam("name") String name) {
        log.info("Getting: " + name);
        return userRepository.findByName(name);
    }
}
