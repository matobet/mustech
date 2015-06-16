package cz.muni.fi.pv243.mustech.model;

import java.io.Serializable;

/**
 * Created by Tomas on 8. 6. 2015.
 */
public class NotificationMessage implements Serializable {
    private final String destination;
    private final String subject;
    private final String message;

    public NotificationMessage(String destination, String subject, String message) {
        this.destination = destination;
        this.subject = subject;
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
