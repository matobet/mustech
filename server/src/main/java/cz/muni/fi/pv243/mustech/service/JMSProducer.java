package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.NotificationMessage;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Enumeration;

/**
 * Created by Tomas on 8. 6. 2015.
 */
@Stateless
public class JMSProducer {
    @Resource(mappedName = "java:jboss/jms/queue/notificationQueue")
    private Queue queue;

    @Inject
    private JMSContext context;

    public void sendMessage(String destination, String message)
    {
            NotificationMessage payload = new NotificationMessage(destination, "Notification", message);
            context.createProducer().send(queue, payload);
    }
}
