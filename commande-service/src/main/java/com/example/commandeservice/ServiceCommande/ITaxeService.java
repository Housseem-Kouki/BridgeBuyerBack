package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Taxe;

import java.util.List;

public interface ITaxeService {

    public List<Taxe> getAllTaxes();
    public Taxe addTaxe(Taxe taxe);
    public Taxe updateTaxe(Taxe taxe);
    public void deleteTaxe(int id);
    public Taxe getTaxeById(int id );
}
