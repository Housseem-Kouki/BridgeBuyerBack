package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Taxe;
import com.example.commandeservice.Repository.TaxeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TaxeServiceImp implements ITaxeService{


    TaxeRepository taxeRepository;
    @Override
    public List<Taxe> getAllTaxes() {
        return taxeRepository.findAll();
    }

    @Override
    public Taxe addTaxe(Taxe taxe) {
        return taxeRepository.save(taxe);
    }

    @Override
    public Taxe updateTaxe(Taxe taxe) {
        return taxeRepository.save(taxe);
    }

    @Override
    public void deleteTaxe(int id) {
    taxeRepository.deleteById(id);
    }

    @Override
    public Taxe getTaxeById(int id) {
        return taxeRepository.findById(id).orElse(null);
    }
}
