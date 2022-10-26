package org.example.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    private static Properties prop = new Properties();

    static {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
    }

    public static void send(String to, String subject, String content) {
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(AppConstants.EMAIL_ACC, AppConstants.EMAIL_APP_CODE);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(AppConstants.EMAIL_ACC));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(content);

            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Message is sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}