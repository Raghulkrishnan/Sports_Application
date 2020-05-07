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
 *SendEmail class is used to send a email to the user's mail ID whenever he
 * sets up a game.
 * Used to send email to both the team coach's email ID about the confirmation of the game.
 * @author raghul
 */
@Named
@RequestScoped
public class SendEmail {

    /**
     *toAddress to store the recipient's email ID
     */
    public static String toAddress;

    /**
     *stores the content of the email
     */
    public static String messageToSend;
    
    /**
     *this method is used to send the email to the recipient with the contents
     */
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

            // adding the sender info
            message.setFrom(new InternetAddress("mailtestfp@gmail.com"));

            // adding the recipient
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

            // adding subject of the mail
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

    /**
     *get value of messagaeToSend
     * @return
     */
    public String getMessageToSend() {
        return messageToSend;
    }

    /**
     *set value of messageToSend
     * @param messageToSend
     */
    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

}
