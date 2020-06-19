package com.infoshareacademy.email;

import com.infoshareacademy.servlet.WelcomeUserServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Stateless
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class.getName());

    final String username = "mindhunters3M@gmail.com";
    final String password = "javaalcoapp3";


    public void sendEmail() {
        Session session = Session.getInstance(getEmailConfigProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Error during sending email, {}", e.getMessage());
        }

    }

    private Properties getEmailConfigProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("session.properties"))
                    .openStream());
        } catch (IOException e) {
            LOGGER.error("Error during loading properties, {}", e.getMessage());
        }
        return properties;
    }

}