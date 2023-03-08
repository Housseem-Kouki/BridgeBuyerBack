package com.example.demandeachatservice.Services.DemandeAchat;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@AllArgsConstructor
public class SendEmailService implements ISendEmailService{

JavaMailSender mailSender;
    public void sendSimpleEmail( String toEmail,
                                 String body,
                                 String subject) {
        System.out.println("sending Email");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("katerkarouf26@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Sent successfully......");
    }

    @Override
    public void sendSimpleEmailWithFils(String toEmail, String body, String subject, String attchment) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);

        mimeMessageHelper.setFrom("katerkarouf26@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attchment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        mailSender.send(message);
        System.out.println("Mail Sent successfully With Attchement ......");
    }



}
