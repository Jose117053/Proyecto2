package com.EquipoQueNoAceptaMasIntegrantes.Controlador.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/** Clase que encarga del manejo del correo del hotel. */
public class Correo {

    /**
     * Manda un correo electrónico.
     * @param to el destinatario del correo.
     * @param subject el asunto del correo.
     * @param text el contenido del correo.
     * @throws MessagingException
     */
    public static void sendEmail(String to, String subject, String text) throws MessagingException {
        // Configuración de las propiedades del servidor SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tu dirección de correo y contraseña
        final String myAccountEmail = "hotelreservaciones24@gmail.com";
        final String password = "hgeuqjrorinzyycx";

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

    /**
     * Prepara el mensaje del correo electrónico.
     * @param session
     * @param myAccountEmail el correo electrónico.
     * @param to el destinatario del correo.
     * @param subject el asunto del correo.
     * @param text el contenido del correo.
     * @return
     */
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
