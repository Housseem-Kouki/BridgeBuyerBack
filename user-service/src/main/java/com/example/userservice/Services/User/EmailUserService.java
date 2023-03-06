package com.example.userservice.Services.User;

import com.example.userservice.Entities.User;
import com.example.userservice.Entities.VerificationToken;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;


@Service
@Transactional
public class EmailUserService {
    private VerificationTokenService verificationTokenService;
    private JavaMailSender javaMailSender;

    public EmailUserService(VerificationTokenService verificationTokenService, JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlMail(User user) throws MessagingException {
        VerificationToken verificationToken = verificationTokenService.findByUser(user);
        if (verificationToken != null){
            String token = verificationToken.getToken();
            String body = "<p>  Click on the link to verify your email address" +" http://0000000kh:9999/USER-SERVICE/activation?token="+token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kuokihoussem@gmail.com");
            message.setTo(user.getEmail());
            message.setText(body);
            message.setSubject("email address verification");
            javaMailSender.send(message);
            System.out.println("Mail send ...");
            /*
            Context context = new Context();
            context.setVariable("title","Verify your email address");
            context.setVariable("link","http://localhost:8085/activation?token="+token);

            //send the verification email
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(user.getEmail());
            helper.setSubject("email address verification");

            helper.setText("Verify your email address \n" +
                    "Click on the link to verify your email address http://localhost:8085/activation?token="+token  );

            javaMailSender.send(message);

            */

        }
    }





}
