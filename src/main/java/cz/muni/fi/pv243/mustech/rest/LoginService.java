package cz.muni.fi.pv243.mustech.rest;

import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class LoginService {

    public static final String USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE = "application/x-authc-username-password+json";
    @Inject
    DefaultLoginCredentials credentials;
    @Inject
    private Identity identity;

    @POST
    @Consumes({USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE})
    public Response authenticate(DefaultLoginCredentials credential) {
        if (!this.identity.isLoggedIn()) {
            this.credentials.setUserId(credential.getUserId());
            this.credentials.setPassword(credential.getPassword());
            this.identity.login();
        }

        Account account = this.identity.getAccount();

        if (account == null) {
            account = new User();
        }

        return Response.ok().entity(account).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes({"*/*"})
    public Response unsupportedCredentialType() {
        return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
    }

}
