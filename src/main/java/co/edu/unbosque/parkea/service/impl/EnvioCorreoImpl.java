package co.edu.unbosque.parkea.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service

public class EnvioCorreoImpl {


    private JavaMailSender mailSender;

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
