package cz.muni.fi.pv243.mustech.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * @author Milan
 */
@Path("/logout")
public class LogoutEndpoint {

    private final Logger log = LoggerFactory.getLogger(LogoutEndpoint.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest req) throws URISyntaxException {
        try {
            log.info(req.getRemoteUser() + " is trying to logout");
            req.logout();
            req.getSession().invalidate();
        } catch (ServletException e) {
            log.error("failed to logout", e);
            return Response.status(Response.Status.BAD_REQUEST).entity("failed to logout").build();
        }
        return Response.status(Response.Status.OK).entity("Logout successful").build();
    }
}
