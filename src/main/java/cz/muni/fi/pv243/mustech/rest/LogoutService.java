package cz.muni.fi.pv243.mustech.rest;

import org.picketlink.Identity;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/logout")
public class LogoutService {

    @Inject
    private Identity identity;

    @POST
    public void logout() {
        if (this.identity.isLoggedIn()) {
            this.identity.logout();
        }
    }

}
