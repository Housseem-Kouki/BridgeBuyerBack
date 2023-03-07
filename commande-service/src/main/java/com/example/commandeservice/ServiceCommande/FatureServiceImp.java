package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.ChargeFinanciere;
import com.example.commandeservice.Entities.Commande;
import com.example.commandeservice.Entities.Facture;
import com.example.commandeservice.Entities.Taxe;
import com.example.commandeservice.Repository.CommandeRepository;
import com.example.commandeservice.Repository.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FatureServiceImp implements  IFactureService{


    FactureRepository factureRepository;
    CommandeRepository commandeRepository;
    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture addFactureAndAssignToCommande(Facture f,int idcommande) {

        Commande commande = commandeRepository.findById(idcommande).orElse(null);

        Set<ChargeFinanciere> charges = f.getListChargeFinancieres();
        Set<Taxe> taxes = f.getTaxes();
        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setListChargeFinancieres(charges);
        facture.setTaxes(taxes);

        // Calcul du montant total de la facture
        double montantTotal = 2;
        // double montantTotal = commande.getOffre().getAppelOffre().;
        for (ChargeFinanciere charge : charges) {
            montantTotal += charge.getMontantCharge();
        }
        for (Taxe taxe : taxes) {
            montantTotal += taxe.getTauxTaxe();
        }
        facture.setMontantFacture(montantTotal);

        // Sauvegarde de la facture en base de données

        return factureRepository.save(facture);
    }


    @Override
    public Facture updateFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(int id) {
            factureRepository.deleteById(id);
    }

    @Override
    public Facture getFactureById(int id) {
        return factureRepository.findById(id).orElse(null);
    }
}
