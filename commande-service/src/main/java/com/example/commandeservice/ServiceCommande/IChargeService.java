package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.ChargeFinanciere;

import java.util.List;

public interface IChargeService {

    public List<ChargeFinanciere> getAllChargeFinancieres();
    public ChargeFinanciere addChargeFinanciere(ChargeFinanciere chargeFinanciere);
    public ChargeFinanciere updateChargeFinanciere(ChargeFinanciere chargeFinanciere);
    public void deleteChargeFinanciere(int id);
    public ChargeFinanciere getChargeFinanciereById(int id );
}
