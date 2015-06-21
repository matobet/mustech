package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.NotificationMessage;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Tomas on 8. 6. 2015.
 */
@Stateless
public class NotificationMailService {

    @Resource(name = "java:/mail/myMailSession")
    private Session mailSession;

    public void sendEmail(String to, String subject, String message)
    {
        try
        {
            MimeMessage mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    public void sendEmail(NotificationMessage message)
    {
        sendEmail(message.getDestination(), message.getSubject(), message.getMessage());
    }
}
