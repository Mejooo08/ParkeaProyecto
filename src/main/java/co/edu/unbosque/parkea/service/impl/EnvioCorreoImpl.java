package co.edu.unbosque.parkea.service.impl;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
public class EnvioCorreoImpl {

    @Autowired
    private JavaMailSender mailSender;

    private EnvioCorreoImpl(){
        this.mailSender = new JavaMailSender() {
            @Override
            public void send(SimpleMailMessage simpleMessage) throws MailException {

            }

            @Override
            public void send(SimpleMailMessage... simpleMessages) throws MailException {

            }

            @Override
            public MimeMessage createMimeMessage() {
                return null;
            }

            @Override
            public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
                return null;
            }

            @Override
            public void send(MimeMessage mimeMessage) throws MailException {

            }

            @Override
            public void send(MimeMessage... mimeMessages) throws MailException {

            }

            @Override
            public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

            }

            @Override
            public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

            }
        };

    }


    public void enviarCorreo(String destino, String asunto, String contenido) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("soporteparkea@gmail.com");
        email.setTo(destino);
        email.setSubject(asunto);
        email.setText(contenido);

        mailSender.send(email);

        System.out.println("Correo enviado exitosamente...");
    }
}
