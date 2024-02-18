package com.nick.product.manage.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${email.password}")
    String emailpassword;

    @Value("${email.username}")
    String username;

    @Value("${provider.host}")
    String host;

    @Value("${provider.port}")
    Integer port;

    public void sendEmail(String toEmail, String password, String imageUri){

        System.out.println("MY EMAIL : "+username);
        System.out.println("MY Password : "+emailpassword);

        // Create properties for the SMTP session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

//        Properties props = new Properties();
//        props.put("mail.smtp.auth", true);
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.mailtrap.io");
//        props.put("mail.smtp.port", "25");
//        props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        // Create a session with the specified properties and authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, emailpassword);
            }
        });

        try {

        // Create a message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Password & QR || LOGIN DETAILS");

        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Create a body part for the email text
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Use this one time auto-generated password to login : "+password);

        // Add the body part to the multipart message
        multipart.addBodyPart(messageBodyPart);

        // Create a body part for the image attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource("path_to_your_image.jpg"); // Replace with the path to your image file
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(imageUri);

        // Add the attachment to the multipart message
        multipart.addBodyPart(attachmentBodyPart);

        // Set the multipart message as the email's content
        message.setContent(multipart);

        // Send the message
        Transport.send(message);

        System.out.println("Email sent successfully.");
    } catch (MessagingException e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
        System.out.println("MESSAGE EXCEPTION");

    }
}


}
