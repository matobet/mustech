package cz.muni.fi.pv243.mustech.rest;

import cz.muni.fi.pv243.mustech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author Milan
 */
@Path("/login")
public class LoginEndpoint {

    private final Logger log = LoggerFactory.getLogger(LoginEndpoint.class);

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest req, @Context HttpServletResponse response) throws IOException, ServletException {
        boolean authenticated = req.authenticate(response);
        log.info("logging in: " + req.getUserPrincipal() + " with success: " + authenticated);
        return Response.status(Response.Status.OK).entity(userService.findByEmail(req.getUserPrincipal().getName())).build();
    }
}
