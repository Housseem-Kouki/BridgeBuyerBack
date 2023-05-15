package com.example.livraisionservice.Service;

import com.example.livraisionservice.Entities.BonReception;
import com.example.livraisionservice.Entities.BonRetour;
import com.example.livraisionservice.Entities.Commande;
import com.example.livraisionservice.Entities.Livraison;
import com.example.livraisionservice.Repository.*;
import com.lowagie.text.Font;

import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public Livraison addAndAssignToCommande(int idCommande, Livraison l){
        Commande commande=commandeRepository.findById(idCommande).orElse(null);



            l.setCommande(commande);

        return livraisonRepository.save(l);
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
        return  livraisonRepository.getLivraisonByFournisseur(idUser);
    }

    //Delete Bon de livraison avec archivage
    @Override
    public void deleteLivraison(int id){
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livraison introuvable"));

        livraison.setArchive(true);
        livraisonRepository.save(livraison);
    }
    //restaure
    @Override
    public void restoreLivraison(int id){
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livraison introuvable"));
        livraison.setArchive(false);
        livraisonRepository.save(livraison);
    }

    //modifier la date de la livraison
    @Override
    public Livraison updateLivraisonDate(int id, Date dateLivraison){
        Livraison livraison = livraisonRepository.findById(id).orElse(null);

        if (livraison == null) {
            throw new IllegalArgumentException("Livraison not found");
        }
        livraison.setDateLivraison(dateLivraison);
        livraison.setEtat("En cours");
        return livraisonRepository.save(livraison);
    }

    //Tri bon livraison selon quantité
    @Override
    public List<Livraison> sortLivraisonsByQuantity() {
        return livraisonRepository.findAllByOrderByQuantiteDelivreAsc();
    }

    //validee bon livraison
    @Override
    public Livraison validerBL(int idLivraison) {
        Livraison livraison = livraisonRepository.findById(idLivraison).orElse(null);

        // Récupérer le bon de réception associé
        BonReception bonReception = livraison.getBonReception();

        // Vérifier si la quantité livrée est égale à la quantité à recevoir
        if (livraison.getQuantiteDelivre() == bonReception.getQuantiteRecevoir()) {
            // Mettre à jour l'état de la livraison en "validé"
            livraison.setEtat("valider");
            livraisonRepository.save(livraison);
        }

        return livraison;
    }
    //recherche avancée
    @Override
    public List<Livraison> rechercheAvance(Integer quantiteDelivre, Date dateLivraison, String etat){
        return livraisonRepository.findAll()
                .stream()
                .filter(livraison -> quantiteDelivre == null || livraison.getQuantiteDelivre()==quantiteDelivre)
                .filter(livraison -> dateLivraison == null || livraison.getDateLivraison()==dateLivraison)
                .filter(livraison -> etat == null || livraison.getEtat().contains(etat))
                .collect(Collectors.toList());
    }
    /*@Override
    public List<Livraison> SearchMultiple(String key) {
        if (key.equals("")) {
            return (List<Livraison>) livraisonRepository.findAll();
        } else {
            return livraisonRepository.recherche(key);
        }
    }*/

    //PDFc
    @Override
    public void export(HttpServletResponse response , int idLivraison) throws IOException {
        Livraison livraison=livraisonRepository.findById(idLivraison).orElse(null);
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        com.lowagie.text.Font fonttitle=FontFactory.getFont(FontFactory.COURIER_BOLD,Font.ITALIC);
        fonttitle.setSize(25);
        fonttitle.setColor(Color.PINK);

        Paragraph paragraph =new Paragraph("Votre Livraison \n \n", fonttitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.COURIER);
        fontParagraph.setSize(18);
        fontParagraph.setColor(Color.BLACK);


        Paragraph p1=new Paragraph("Quantite delivree:  "+livraison.getQuantiteDelivre(), fontParagraph);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        //Paragraph p2=new Paragraph("Quantitée retourné:  "+livraison.getQuantiteRetour(), fontParagraph);
        //p2.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2=new Paragraph("Date livraison:  "+livraison.getDateLivraison(), fontParagraph);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p3=new Paragraph("Etat Livraison:  "+livraison.getEtat(), fontParagraph);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        //document.add(p4);


        document.close();
    }

    //upload file




}


