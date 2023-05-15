package com.example.commandeservice.Controller;

import com.example.commandeservice.Entities.Paiment;
import com.example.commandeservice.ServiceCommande.ICommandeService;
import com.example.commandeservice.ServiceCommande.IPaimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PayementController {




    @Autowired
    ICommandeService iCommandeService;
    @Autowired
    IPaimentService iPaimentService;
    @PostMapping("/payer")
    public Paiment chargeCreditCard(@RequestBody Paiment paymentRequest) {
        return iPaimentService.PayerCommandesAndAddPaiment(paymentRequest);
    }
}