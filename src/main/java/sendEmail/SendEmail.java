/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendEmail;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;

import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author raghul
 */
@Named
@RequestScoped
public class SendEmail {

    public static String toAddress;
    public static String messageToSend;
    
    public static void sendMailToCoach() {
        
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mailtestfp@gmail.com", "Mail@123");
            }
        });
        
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("mailtestfp@gmail.com"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

            // Set Subject: header field
            message.setSubject("Hello from Sport Connect!");

            // message to be sent
            message.setText(messageToSend);

            System.out.println("=============sending mail........");
            // Send message
            Transport.send(message);
            System.out.println("=================Mail sent....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /**
     * Get the value of toAddress
     *
     * @return the value of toAddress
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * Set the value of toAddress
     *
     * @param toAddress new value of toAddress
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
    public String getMessageToSend() {
        return messageToSend;
    }
    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

}
