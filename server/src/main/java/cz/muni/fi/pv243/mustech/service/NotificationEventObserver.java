package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.User;
import cz.muni.fi.pv243.mustech.util.UserNotification;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Created by Tomas on 13. 6. 2015.
 */
@ApplicationScoped
public class NotificationEventObserver {

    @Inject
    private JMSProducer producer;


    private void observe(@Observes UserNotification event)
    {
        for(User user : event.getUsers())
        {
            producer.sendMessage(user.getEmail(), generateMessageBody(event.getIssue()));
        }
    }

    private static String generateMessageBody(Issue issue)
    {
        return "Hi, "
                + issue.getCreatedBy().getName()
                + " want to know your opinion about " + issue.getName()
                + ". For response, please visit the web page.";
    }
}
