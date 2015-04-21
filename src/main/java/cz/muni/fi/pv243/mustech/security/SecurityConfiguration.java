package cz.muni.fi.pv243.mustech.security;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

import javax.enterprise.event.Observes;

public class SecurityConfiguration {

    public void init(@Observes SecurityConfigurationEvent event) {
        SecurityConfigurationBuilder builder = event.getBuilder();

        builder
            .identity()
                .stateless()
            .idmConfig()
                .named("jpa.config")
                    .stores()
                        .jpa().supportAllFeatures();
    }
}
