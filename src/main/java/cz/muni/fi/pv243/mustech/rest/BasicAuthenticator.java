package cz.muni.fi.pv243.mustech.rest;


import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@PicketLink
public class BasicAuthenticator extends BaseAuthenticator {

    @Inject
    private DefaultLoginCredentials credentials;

    @Override
    public void authenticate() {
        if (this.credentials.getCredential() == null) {
            return;
        }

        if (isUsernamePasswordCredential()) {
            String userId = this.credentials.getUserId();
            Password password = (Password) this.credentials.getCredential();

            if (userId.equals("test") && String.valueOf(password.getValue()).equals("12345678")) {
                successfulAuthentication();
            }
        }
    }

    private boolean isUsernamePasswordCredential() {
        return Password.class.equals(credentials.getCredential().getClass()) && credentials.getUserId() != null;
    }

    private User getDefaultUser() {
        return new User("test");
    }

    private void successfulAuthentication() {
        setStatus(AuthenticationStatus.SUCCESS);
        setAccount(getDefaultUser());
    }

}
