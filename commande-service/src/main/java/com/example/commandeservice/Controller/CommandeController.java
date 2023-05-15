package com.example.commandeservice.Controller;

import com.example.commandeservice.Entities.*;
import com.example.commandeservice.Repository.UserRepository;
import com.example.commandeservice.ServiceCommande.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Commandes")
@CrossOrigin(origins = "*")
public class CommandeController {

    UserRepository userRepository;
    ICommandeService iCommandeService;
    ITaxeService    iTaxeService;
    IPaimentService iPaimentService;
    IFactureService iFactureService;
    IChargeService iChargeService;


    //---------------------------- Commande --------------------------------------//

    // operateur get all commandes
    //
    @GetMapping("/getAllCommandes")
    public List<Commande> getAllCommandes(){
        return iCommandeService.getAllCommandes();
    }


    // Acheteur  accepte  offre et cretion d'une commande
    @PostMapping("/addCommandeAndAssignToOffre/{id}")
    @ResponseBody
    public Commande addCommandeAndAssignOffre(@RequestBody Commande c,@PathVariable("id") int id) {
        return iCommandeService.addCommandeAndAssignOffre(c,id);
    }
    // operateur peux changer les commande
    @PutMapping("/updateCommande")
    @ResponseBody
    public Commande updateCommande( @RequestBody Commande c) {
        return iCommandeService.updateCommande(c);
    }

    //Operateur supprimer les commandes
    @DeleteMapping("/deleteCommande/{id}")
    public void deleteCommande( @PathVariable("id") int id) {
        iCommandeService.deleteCommande(id);

    }
    // details commandes pour Acheteur
    @GetMapping("/getCommandeById/{id}")
    public Commande getCommandeById( @PathVariable("id") int id) {
        return iCommandeService.getCommandeById(id);

    }
    // les commande leier a acheteur connecter

    @GetMapping("/getCommandeByUser/{id}")
    public List<Commande> getCommandeByUser( @PathVariable("id") int id){
        return iCommandeService.getCommandeByUser(id);

    }
// commande fournisseur lier a ces devis

    @GetMapping("/getCommandeFournisuer")
    @CrossOrigin(origins = "*")
    public List<Commande> getCommandeFournisuer(Principal principal){
        return iCommandeService.getCommandeFournisuer(principal);

    }
    // getCommande By etat filttrage
    @GetMapping("/getCommandeByEtat/{etat}")
    public List<Commande> getCommandeByEtatFourniseur(@PathVariable("etat") int etat,Principal principal){
        return iCommandeService.getCommandeByEtat(etat,principal);

    }


    //---------------------------- Taxe --------------------------------------//
    @GetMapping("/getAllTaxes")
    public List<Taxe> getAllTaxes(){
        return iTaxeService.getAllTaxes();


    }
    @PostMapping("/addTaxe")
    @ResponseBody
    public Taxe addTaxe(@RequestBody Taxe c) {
        return iTaxeService.addTaxe(c);
    }
    @PutMapping("/updateTaxe")
    @ResponseBody
    public Taxe updateTaxe( @RequestBody Taxe c) {
        return iTaxeService.updateTaxe(c);
    }
    @DeleteMapping("/deleteTaxe/{id}")
    public void deleteTaxe( @PathVariable("id") int id) {
        iTaxeService.deleteTaxe(id);

    }
    @GetMapping("/getTaxeById/{id}")
    public Taxe getTaxeById( @PathVariable("id") int id) {
        return iTaxeService.getTaxeById(id);

    }

    //---------------------------- Paiment --------------------------------------//

    @GetMapping("/getAllPaiments")
    public List<Paiment> getAllPaiments(){
        return iPaimentService.getAllPaiments();


    }

    @PutMapping("/updatePaiment")
    @ResponseBody
    public Paiment updatePaiment( @RequestBody Paiment c) {
        return iPaimentService.updatePaiment(c);
    }
    @DeleteMapping("/deletePaiment/{id}")
    public void deletePaiment( @PathVariable("id") int id) {
        iPaimentService.deletePaiment(id);

    }
    @GetMapping("/getPaimentById/{id}")
    public Paiment getPaimentById( @PathVariable("id") int id) {
        return iPaimentService.getPaimentById(id);

    }
    //---------------------------- Facture --------------------------------------//

    @GetMapping("/getAllFactures")
    public List<Facture> getAllFactures(){
        return iFactureService.getAllFactures();


    }

    // marche cote operateur
    @PostMapping("/addFactureAndAssignToCommande/{idcommande}")
    @ResponseBody
    public Facture addFactureAndAssignToCommande(@RequestBody Facture f, @PathVariable("idcommande") int idcommande  ){

        return iFactureService.addFactureAndAssignToCommande(f,idcommande);
    }
    @PutMapping("/updateFacture")
    @ResponseBody
    public Facture updateFacture( @RequestBody Facture c) {
        return iFactureService.updateFacture(c);
    }
    @DeleteMapping("/deleteFacture/{id}")
    public void deleteFacture( @PathVariable("id") int id) {
        iFactureService.deleteFacture(id);

    }
    @GetMapping("/getFactureById/{id}")
    public Facture getFactureById( @PathVariable("id") int id) {
        return iFactureService.getFactureById(id);

    }
    //---------------------------- ChargeFinanciere --------------------------------------//

    @GetMapping("/getAllChargeFinancieres")
    public List<ChargeFinanciere> getAllChargeFinancieres(){
        return iChargeService.getAllChargeFinancieres();
    }
    @PostMapping("/addChargeFinanciere")
    @ResponseBody
    public ChargeFinanciere addChargeFinanciere(@RequestBody ChargeFinanciere c) {
        return iChargeService.addChargeFinanciere(c);
    }
    @PutMapping("/updateChargeFinanciere")
    @ResponseBody
    public ChargeFinanciere updateChargeFinanciere( @RequestBody ChargeFinanciere c) {
        return iChargeService.updateChargeFinanciere(c);
    }
    @DeleteMapping("/deleteChargeFinanciere/{id}")
    public void deleteChargeFinanciere( @PathVariable("id") int id) {
        iChargeService.deleteChargeFinanciere(id);

    }

    @GetMapping("/getChargeFinanciereById/{id}")
    public ChargeFinanciere getChargeFinanciereById( @PathVariable("id") int id) {
        return iChargeService.getChargeFinanciereById(id);

    }

    // operateur/fourniseur
    @GetMapping("/pdf/generate/{idFacture}")
    public void generatePdf(HttpServletResponse response , @PathVariable("idFacture") int idFacture) throws IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDate= dateFormat.format(new Date());
        String headerkey="Content-Disposition";
        String headerValue="attachement; filename=pdf_"+ currentDate + ".pdf";
        response.setHeader(headerkey, headerValue);
        this.iFactureService.export(response,idFacture);
    }
    // operateur
    @PutMapping("/annulerCommande/{idCommande}")
    public Commande annulerCommande( @PathVariable("idCommande") int idCommande) {
        return  iCommandeService.annulerCommande(idCommande);
    }

    // sa marche cote operateur seul
    @PutMapping("/reactiver/{idCommande}")
    public Commande reactiver( @PathVariable("idCommande") int idCommande) {
        return  iCommandeService.reactiver(idCommande);
    }
}