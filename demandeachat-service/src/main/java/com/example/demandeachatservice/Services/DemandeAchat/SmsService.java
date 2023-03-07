package com.example.demandeachatservice.Services.DemandeAchat;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class SmsService {
    private final String ACCOUNT_SID ="ACe012c454f3cf736932fd69bdb7f07d2a";

    private final String AUTH_TOKEN = "c0532acead7eee416874bec924800fac";

    private final String FROM_NUMBER = "+12762966089";

    public void send(String to , String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message1 = Message.creator(new PhoneNumber(to), new PhoneNumber(FROM_NUMBER), message)
                .create();
        System.out.println("here is my id:"+message1.getSid());// Unique resource ID created to manage this transaction*/



    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}
