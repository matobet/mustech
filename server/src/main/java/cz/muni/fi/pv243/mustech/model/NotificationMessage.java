package cz.muni.fi.pv243.mustech.model;

import java.io.Serializable;

/**
 * Created by Tomas on 8. 6. 2015.
 */
public class NotificationMessage implements Serializable {
    private final String destination;
    private final String Subject;
    private final String Message;

    public NotificationMessage(String destination, String subject, String message) {
        this.destination = destination;
        Subject = subject;
        Message = message;
    }

    public String getDestination() {
        return destination;
    }

    public String getSubject() {
        return Subject;
    }

    public String getMessage() {
        return Message;
    }
}
