package com.EquipoQueNoAceptaMasIntegrantes.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Correo {

    public static void sendEmail(String to, String subject, String text) throws MessagingException {
        // Configuración de las propiedades del servidor SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tu dirección de correo y contraseña
        final String myAccountEmail = "rayaperezjoseluis@gmail.com";
        final String password = "snpixqmguqqvuznr";

        // Crear una sesión con autenticación
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        // Crear el mensaje de correo electrónico
        Message message = prepareMessage(session, myAccountEmail, to, subject, text);

        // Enviar el correo
        Transport.send(message);
        System.out.println("Mensaje enviado exitosamente");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String to, String subject,
            String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
