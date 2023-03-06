package com.example.commandeservice.ServiceCommande;

import com.stripe.Stripe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public StripeService() {
        Stripe.apiKey = secretKey;
    }
}
