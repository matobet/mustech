package cz.muni.fi.pv243.mustech.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Milan
 */
@Path("/login")
public class LoginEndpoint {

    private final Logger log = LoggerFactory.getLogger(LoginEndpoint.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void login(@Context HttpServletRequest req) {
        log.info("logging in: " + req.getUserPrincipal());
    }
}
