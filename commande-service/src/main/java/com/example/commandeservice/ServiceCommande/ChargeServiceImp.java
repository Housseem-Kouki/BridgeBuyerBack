package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.ChargeFinanciere;
import com.example.commandeservice.Repository.ChargeFinanciereRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ChargeServiceImp implements IChargeService{

    ChargeFinanciereRepository  chargeFinanciereRepository;
    @Override
    public List<ChargeFinanciere> getAllChargeFinancieres() {
        return chargeFinanciereRepository.findAll();
    }

    @Override
    public ChargeFinanciere addChargeFinanciere(ChargeFinanciere chargeFinanciere) {
        return chargeFinanciereRepository.save(chargeFinanciere);
    }

    @Override
    public ChargeFinanciere updateChargeFinanciere(ChargeFinanciere chargeFinanciere) {
        return chargeFinanciereRepository.save(chargeFinanciere);
    }

    @Override
    public void deleteChargeFinanciere(int id) {
  chargeFinanciereRepository.deleteById(id);
    }

    @Override
    public ChargeFinanciere getChargeFinanciereById(int id) {
        return chargeFinanciereRepository.findById(id).orElse(null);
    }
}
