package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.NotificationMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * JMS consumer for user notification
 * Created by Tomas on 8. 6. 2015.
 */
@MessageDriven(
        mappedName = "java:jboss/jms/queue/notificationQueue",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/queue/notificationQueue")
        }
)
public class JMSConsumer implements MessageListener {

    @Inject
    private NotificationMailService notificationMailService;

    @Override
    public void onMessage(Message message) {
        try {
            NotificationMessage payload = message.getBody(NotificationMessage.class);

            notificationMailService.sendEmail(payload);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
