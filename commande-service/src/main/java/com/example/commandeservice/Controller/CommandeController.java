package com.example.commandeservice.Controller;

import com.example.commandeservice.Entities.*;
import com.example.commandeservice.ServiceCommande.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Commandes")
public class CommandeController {
    
    
    ICommandeService iCommandeService;
    ITaxeService    iTaxeService;
    IPaimentService iPaimentService;
    IFactureService iFactureService;
    IChargeService iChargeService;


    //---------------------------- Commande --------------------------------------//


    @GetMapping("/getAllCommandes")
    public List<Commande> getAllCommandes(){
        return iCommandeService.getAllCommandes();


    }
    @PostMapping("/addCommandeAndAssignToOffre/{id}")
    @ResponseBody
    public Commande addCommandeAndAssignOffre(@RequestBody Commande c,@PathVariable("id") int id) {


        return iCommandeService.addCommandeAndAssignOffre(c,id);
    }
    @PutMapping("/updateCommande")
    @ResponseBody
    public Commande updateCommande( @RequestBody Commande c) {
        return iCommandeService.updateCommande(c);
    }
    @DeleteMapping("/deleteCommande/{id}")
    public void deleteCommande( @PathVariable("id") int id) {
        iCommandeService.deleteCommande(id);

    }
    @GetMapping("/getCommandeById/{id}")
    public Commande getCommandeById( @PathVariable("id") int id) {
        return iCommandeService.getCommandeById(id);

    }

    @GetMapping("/getCommandeByUser")
    public List<Commande> getCommandeByUser(){
        return iCommandeService.getCommandeByUser();

    }

    @GetMapping("/getCommandeFournisuer")
    public List<Commande> getCommandeFournisuer(){
        return iCommandeService.getCommandeFournisuer();

    }
    @GetMapping("/getCommandeByEtat/{etat}")
    public List<Commande> getCommandeByEtat(@PathVariable("etat") int etat){
        return iCommandeService.getCommandeByEtat(etat);

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
    @PostMapping("/addFactureAndAssignToCommande/{idcommande}")
    @ResponseBody
    public Facture addFactureAndAssignToCommande(@RequestBody Facture f, @PathVariable("idcommande") int idcommande ){
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




}
