package com.example.demandeachatservice.Services.DemandeAchat;

import javax.mail.MessagingException;

public interface ISendEmailService {
    void sendSimpleEmail( String toEmail,
                          String body,
                          String subject);


    void sendSimpleEmailWithFils( String toEmail, String body, String subject, String attchment) throws MessagingException;

}
