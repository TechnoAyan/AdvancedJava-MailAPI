/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EMail {
    public static boolean sendFromGMail(String to, String subject, String body) {
        boolean flag=false;
        Properties props = System.getProperties();
        String from="sender@gmail.com";
        String pass="abc123";
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("mail sent");
            flag=true;
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        return flag;
    }
    public static void main(String[] args){
        sendFromGMail("s.c1084@gmail.com", "TIU LIBRARY WELCOME", "hello");
    }
}
