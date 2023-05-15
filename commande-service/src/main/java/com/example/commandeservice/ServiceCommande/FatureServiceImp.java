package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.ChargeFinanciere;
import com.example.commandeservice.Entities.Commande;
import com.example.commandeservice.Entities.Facture;
import com.example.commandeservice.Entities.Taxe;
import com.example.commandeservice.Repository.CommandeRepository;
import com.example.commandeservice.Repository.FactureRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.stripe.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
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
    // operateur creation facture et assign to commande
    public Facture addFactureAndAssignToCommande(Facture facture, int idcommande) {
        double montantTotalCharge = 0 ,pourcentageTotalTaxe = 0 ,montantTotalAvecTaxe ;
        Commande commande = commandeRepository.findById(idcommande).orElse(null);
        Set<ChargeFinanciere> charges = facture.getListChargeFinancieres();
        Set<Taxe> taxes = facture.getTaxes();
        facture.setCommande(commande);
        facture.setDateFacture(new Date());

        double montantTotalcmd= commande.getPrixTotalHorsTaxe();
        // taxe calcule par %
        for (ChargeFinanciere charge : charges) {
            if(charge.getFactures()==null)
            {

                charge.setFactures(new HashSet<>());
            }
            charge.getFactures().add(facture);
            montantTotalCharge += charge.getMontantCharge();}
        for (Taxe taxe : taxes) {
            if(taxe.getFactures()==null)
            {

                taxe.setFactures(new HashSet<>());
            }
            taxe.getFactures().add(facture);
            pourcentageTotalTaxe +=  taxe.getTauxTaxe();}
        montantTotalAvecTaxe = (montantTotalcmd*(pourcentageTotalTaxe/100))+montantTotalCharge+montantTotalcmd;    // montant total commande * pourcentage de tous les taxe + montant des charge + le montant total de la commande avent les modification
        if (montantTotalAvecTaxe>1000 && montantTotalAvecTaxe<10000) { // remise pour les commande entre 1000 et 10 000
            montantTotalAvecTaxe = montantTotalAvecTaxe*0.95;
            commande.setPrixTotalAvecTaxe(montantTotalAvecTaxe);
            commande.setEtatCommande(1);
            facture.setMontantFacture(montantTotalAvecTaxe);
        }
        // remise commande  superieur a 10 000
        else if(montantTotalAvecTaxe>10000) {
            montantTotalAvecTaxe = montantTotalAvecTaxe*0.9;
            commande.setPrixTotalAvecTaxe(montantTotalAvecTaxe);
            commande.setEtatCommande(1);
            facture.setMontantFacture(montantTotalAvecTaxe);

        }
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

    @Override
    public void export(HttpServletResponse response , int idFacture) throws IOException {
        //  List<AppelOffre> appelOffre=appelOffreRepository.findAll();
        Facture facture = factureRepository.findByIdWithCommande(idFacture);

        List<ChargeFinanciere> charges = factureRepository.findChargesByFactureId(idFacture);
        List<Taxe> taxes = factureRepository.findTaxesByFactureId(idFacture);
        if (facture.getCommande().getEtatCommande() == 2) {

            com.lowagie.text.Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD, Font.ITALIC);
            fontTitle.setSize(25);
            fontTitle.setColor(Color.PINK);

            Paragraph paragraph = new Paragraph("Facture numero" + facture.getIdFacture() + "\n \n", fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);

            Font fontParagraph = FontFactory.getFont(FontFactory.COURIER);
            fontParagraph.setSize(18);
            fontParagraph.setColor(Color.BLACK);


            Paragraph p1 = new Paragraph("Commentaire du client:  " + facture.getIdFacture(), fontParagraph);
            p1.setAlignment(Paragraph.ALIGN_LEFT);


            Paragraph p2 = new Paragraph("Prix initiale proposé par le client:  " + facture.getCommande().getPrixTotalAvecTaxe(), fontParagraph);
            p2.setAlignment(Paragraph.ALIGN_LEFT);
            Paragraph p6 = new Paragraph("Prix initiale proposé par le client:  " + facture.getCommande().getPrixTotalAvecTaxe(), fontParagraph);
            p2.setAlignment(Paragraph.ALIGN_LEFT);
            p2.setAlignment(Paragraph.ALIGN_LEFT);
            Paragraph p3 = new Paragraph("Prix initiale proposé par le client:  " + facture.getDescription(), fontParagraph);
            p3.setAlignment(Paragraph.ALIGN_LEFT);
            Paragraph p4 = new Paragraph("Date de creation de l'appel d'offre:  " + facture.getDateFacture(), fontParagraph);
            p4.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);
            document.add(p1);
            document.add(p2);
            document.add(p3);
            document.add(p4);

            for (Taxe taxe : taxes) {
                Paragraph p5 = new Paragraph("Taxe " + taxe.getNomTaxe() + " (" + taxe.getTauxTaxe() + "%): " + (facture.getMontantFacture() * taxe.getTauxTaxe() / 100), fontParagraph);
                p5.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(p5);
            }

            for (ChargeFinanciere charge : charges) {
                Paragraph p7 = new Paragraph("Charge financière " + charge.getNomCharge() + ": " + charge.getMontantCharge(), fontParagraph);
                p7.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(p7);
            }
            document.close();
        } else
        {

            System.out.println("commande pas encore payer");

        }

        //AppelOffre(appelOffre.getCommentaire(),fontParagraph);
    }
}