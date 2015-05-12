package cz.muni.fi.pv243.mustech.security;

import org.picketbox.http.config.ConfigurationBuilderProvider;
import org.picketbox.http.config.HTTPConfigurationBuilder;
import org.picketbox.http.resource.ProtectedResourceConstraint;

import javax.servlet.ServletContext;

/**
 * @author Milan
 */
public class SecurityConfigurer implements ConfigurationBuilderProvider {

    @Override
    public HTTPConfigurationBuilder getBuilder(ServletContext servletcontext) {
        HTTPConfigurationBuilder configurationBuilder = new HTTPConfigurationBuilder();

        configurationBuilder.identityManager().jpaStore();

        configurationBuilder.protectedResource()
                .resource("/api/login", ProtectedResourceConstraint.NOT_PROTECTED)
                .resource("/api/logout", ProtectedResourceConstraint.NOT_PROTECTED)
                .resource("/api/signup", ProtectedResourceConstraint.NOT_PROTECTED)
                .resource("/api/*", ProtectedResourceConstraint.AUTHENTICATION)
                .resource("/*", ProtectedResourceConstraint.NOT_PROTECTED);

        return configurationBuilder;
    }

}
