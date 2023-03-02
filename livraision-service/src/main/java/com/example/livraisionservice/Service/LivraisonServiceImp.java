package com.example.livraisionservice.Service;

import com.example.livraisionservice.Entities.BonReception;
import com.example.livraisionservice.Entities.Commande;
import com.example.livraisionservice.Entities.Livraison;
import com.example.livraisionservice.Repository.BonReceptionRepository;
import com.example.livraisionservice.Repository.BonRetourRepository;
import com.example.livraisionservice.Repository.CommandeRepository;
import com.example.livraisionservice.Repository.LivraisonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class LivraisonServiceImp implements ILivraisonService {
    LivraisonRepository livraisonRepository;
    BonReceptionRepository bonReceptionRepository;
    BonRetourRepository bonRetourRepository;
    private CommandeRepository commandeRepository;

    //Creation bon de livraison
    //Fournisseur
    //add :récupération details bon de commande + bouton crée bon de livraison
    @Override
    public Livraison addLivraison(Livraison l) {
        return livraisonRepository.save(l);
    }

    public Livraison addAndAssignToCommande(int idCommande, Livraison l) {
        Commande commande = commandeRepository.findById(idCommande).orElse(null);
        if (commande.getEtatCommande() == 1) {
            l.setCommande(commande);
            return livraisonRepository.save(l);
        }
        return null;
    }

    //Consuleter bon de livraison
    //opérateur
    @Override
    public Livraison getLivraisonById(int id) {
        return livraisonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Livraison> getAllLivraison() {
        return livraisonRepository.findAll();
    }

    //Acheteur
    @Override
    public List<Livraison> getAllLivraisonUserContecter(Integer idUser) {
        return livraisonRepository.getLivraisonByCommande(idUser);
    }

    //fournisseur
    @Override
    public List<Livraison> getLivraisonByFournisseur(Integer idUser) {
        return livraisonRepository.getLivraisonByFournisseur(idUser);
    }


    //affecter Bon de reception a un bon de livraison
    public Livraison affecterBonReceptToBonLivr(int idBonReception, int idLivraision) {
        Livraison livraison = livraisonRepository.findById(idLivraision).orElse(null);
        BonReception bonReception = bonReceptionRepository.findById(idBonReception).orElse(null);
        bonReception.setLivraision(livraison);
        // livraison.setBonReception(bonReception);
        return livraisonRepository.save(livraison);
    }

    //search
    @Override
    public List<Livraison> SearchMultiple(String key) {
        if (key.equals("")) {
            return (List<Livraison>) livraisonRepository.findAll();
        } else {
            return livraisonRepository.recherche(key);
        }
    }

    //Delete Bon de livraison avec archivage
    public void deleteLivraison(int id) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livraison introuvable"));

        livraison.setArchive(true);
        livraisonRepository.save(livraison);
    }

    //Get une livraison par commande
  /*  public List<Livraison> getCommandeByLivraison(int idCommande){
        return livraisonRepository.getLivraisonByCommande(idCommande);    }*/

    //modifier la date de la livraison
    public Livraison updateLivraisonDate(int id, Date dateLivraison) {
        Livraison livraison = livraisonRepository.findById(id).orElse(null);

        if (livraison == null) {
            throw new IllegalArgumentException("Livraison not found");
        }
        livraison.setDateLivraison(dateLivraison);
        return livraisonRepository.save(livraison);
    }

    //Tri bon livraison selon quantité
    public List<Livraison> sortLivraisonsByQuantity() {
        return livraisonRepository.findAllByOrderByQuantiteDelivreAsc();
    }

    //Modifier Bon livraison
    public void modifierQuantiteLivraison(int idLivraison, Integer qteRecue, Integer qteRetour) {

        Livraison livraison = livraisonRepository.findById(idLivraison).orElseThrow(() -> new EntityNotFoundException("Livraison introuvable"));

        livraison.setQuantiteDelivre(qteRecue);
        livraison.setQuantiteRetour(qteRetour);

        livraisonRepository.save(livraison);
    }

    //validee bon livraison
    public Livraison validerBL(int idLivraison) {
        Livraison livraison = livraisonRepository.findById(idLivraison).orElse(null);

        // Récupérer le bon de réception associé
        BonReception bonReception = livraison.getBonReception();

        // Vérifier si la quantité livrée est égale à la quantité à recevoir
        if (livraison.getQuantiteDelivre() == bonReception.getQuantiteRecevoir()) {
            // Mettre à jour l'état de la livraison en "validé"
            livraison.setEtat("valider+");
            livraisonRepository.save(livraison);
        }

        return livraison;
    }


    //PDF

}


