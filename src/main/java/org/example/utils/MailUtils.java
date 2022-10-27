package org.example.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MailUtils {

    private static Properties prop = new Properties();

    static {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
    }


    private static Session getSession(){
        return Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(AppConstants.EMAIL_ACC, AppConstants.EMAIL_APP_CODE);
                    }
                });
    }

    public static void send(String to, String subject, String content) {
        try {
            Message message = prepareNessage(to, subject);
            message.setText(content);

            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Message is sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    public static void send(String to, String subject, String content, String path) {
        try {
            Message message = prepareNessage(to, subject);

//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(content);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(content, "text/html");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(path));

            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart); //TODO check HTML

            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Message is sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Message prepareNessage(String to, String subject) throws MessagingException {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(AppConstants.EMAIL_ACC));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        return message;
    }
}