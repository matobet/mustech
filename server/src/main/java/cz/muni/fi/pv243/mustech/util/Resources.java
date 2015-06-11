package cz.muni.fi.pv243.mustech.util;

import javax.enterprise.inject.Produces;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = "java:jboss/jms/queue/notificationQueue",
                interfaceName = "javax.jms.Queue",
                destinationName = "notification"
        )
})
public class Resources {

    @Produces
    @PersistenceContext(unitName = "mustech")
    private EntityManager entityManager;
}
