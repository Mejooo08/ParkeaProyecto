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

    /**
     * Este método se usa para implementar el envío de correos
     */
    private EnvioCorreoImpl(){
        this.mailSender = new JavaMailSender() {
            /**
             * Este método se usa para enviar
             * @param simpleMessage
             * @throws MailException
             */
            @Override
            public void send(SimpleMailMessage simpleMessage) throws MailException {

            }

            /**
             * Este método se usa para enviar
             * @param simpleMessages
             * @throws MailException
             */
            @Override
            public void send(SimpleMailMessage... simpleMessages) throws MailException {

            }

            /**
             * Este método se usa para crear mensajes
             * @return
             */
            @Override
            public MimeMessage createMimeMessage() {
                return null;
            }

            /**
             * Este método se usa para crear mensajes
             * @param contentStream
             * @return
             * @throws MailException
             */
            @Override
            public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
                return null;
            }

            /**
             * Este método se usa para enviar
             * @param mimeMessage
             * @throws MailException
             */

            @Override
            public void send(MimeMessage mimeMessage) throws MailException {

            }

            /**
             * Este método se usa para enviar
             * @param mimeMessages
             * @throws MailException
             */
            @Override
            public void send(MimeMessage... mimeMessages) throws MailException {

            }

            /**
             * Este método se usa para enviar
             * @param mimeMessagePreparator
             * @throws MailException
             */
            @Override
            public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

            }

            /**
             * Este método se usa para enviar
             * @param mimeMessagePreparators
             * @throws MailException
             */
            @Override
            public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

            }
        };

    }

    /**
     * Este método se usa para enviar un correo
     * @param destino
     * @param asunto
     * @param contenido
     */

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
