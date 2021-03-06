package cz.muni.fi.pv243.mustech.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Endpoint for user logout
 * @author Milan
 */
@Path("/logout")
public class LogoutEndpoint {

    private final Logger log = LoggerFactory.getLogger(LogoutEndpoint.class);

    @POST
    @PermitAll
    public Response logout(@Context HttpServletRequest req) throws URISyntaxException {
        try {
            log.info(req.getRemoteUser() + " is trying to logout");
            req.logout();
            req.getSession().invalidate();
        } catch (ServletException e) {
            log.error("failed to logout", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
