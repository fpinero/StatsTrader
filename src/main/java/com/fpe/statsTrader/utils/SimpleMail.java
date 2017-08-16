package com.fpe.statsTrader.utils;

import javax.faces.bean.ManagedBean;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.util.Properties;

@Component
@ManagedBean
public class SimpleMail {

    private static final String SMTP_HOST_NAME = "mail.n0-reply.com";
    private static final String SMTP_AUTH_USER = "statstrader@n0-reply.com";
    private static final String SMTP_AUTH_PWD  = "PlOkIj123-";

    

    public void send(String to, String subject, String body) throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.localhost", SMTP_AUTH_USER);  //esto es un requisito nuevo.

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getInstance(props, auth);
//        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject, "text/plain");
        message.setContent(body, "text/plain");
        message.setFrom(new InternetAddress("statstrader@n0-reply.com"));
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress(to));

        //transport.connect();
        transport.connect(SMTP_HOST_NAME, 26, SMTP_AUTH_USER, SMTP_AUTH_PWD);
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
}
