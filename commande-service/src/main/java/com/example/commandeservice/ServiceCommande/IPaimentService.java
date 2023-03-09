package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Paiment;

import java.util.List;

public interface IPaimentService {

    public List<Paiment> getAllPaiments();
    public Paiment PayerCommandesAndAddPaiment(Paiment paiment);
    public Paiment updatePaiment(Paiment paiment);
    public void deletePaiment(int id);
    public Paiment getPaimentById(int id );

}