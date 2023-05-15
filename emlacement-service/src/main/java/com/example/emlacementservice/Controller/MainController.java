package com.example.emlacementservice.Controller;


import java.util.List;

import javax.websocket.server.PathParam;

import com.example.emlacementservice.Entities.AdresseDepartement;
import com.example.emlacementservice.Entities.AdresseExpedition;
import com.example.emlacementservice.Entities.Departement;
import com.example.emlacementservice.Entities.Emplacement;
import com.example.emlacementservice.Service.IAdresseDepartementService;
import com.example.emlacementservice.Service.IAdresseExpeditionService;
import com.example.emlacementservice.Service.IDepartementService;
import com.example.emlacementservice.Service.IEmplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.emlacementservice.Repository.ComplaintsRepository;
import com.example.emlacementservice.Service.ComplaintsService;
import com.example.emlacementservice.Entities.Complaints;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
    IDepartementService iDepartementService;
    IEmplacementService iEmplacementService;
    IAdresseDepartementService iAdresseDepartementService;
    IAdresseExpeditionService iAdresseExpeditionService;
    ComplaintsService complaintsService;
    ComplaintsRepository complaintsRepository;

    JavaMailSender emailSender;


    @GetMapping("/AllDepartements")
    @ResponseBody
    public List<Departement> getAllDepartements() {
        return iDepartementService.getAllDepartements();
    }

    @PostMapping("/addDepartement/{idE}")
    @ResponseBody
    public Departement addDepartement(@RequestBody Departement Departement,@PathVariable Integer idE) {
        return iDepartementService.addDepartement(Departement,idE);
    }

    @GetMapping("/getDepartementById/{id}")
    @ResponseBody
    public Departement getDepartementById(@PathVariable("id") int id) {
        return iDepartementService.getDepartementById(id);
    }


    @DeleteMapping("/deleteDepartement/{id}")
    private void deleteDepartement(@PathVariable("id") int id) {
        iDepartementService.deleteDepartement(id);
    }

    @PutMapping("/updateDepartement")
    private Departement updateDepartement(@RequestBody Departement Departement) {
        iDepartementService.updateDepartement(Departement);
        return Departement;
    }


    /**************************************************************************************************/
    @GetMapping("/AllEmplacements")
    @ResponseBody
    public List<Emplacement> getAllEmplacements() {
        return iEmplacementService.getAllEmplacements();
    }

    @PostMapping("/addEmplacement/{idA}")
    @ResponseBody
    public Emplacement addEmplacement(@RequestBody Emplacement Emplacement , @PathVariable int idA) {
        return iEmplacementService.addEmplacement(Emplacement,idA);
    }

    @GetMapping("/getEmplacementById/{id}")
    @ResponseBody
    public Emplacement getEmplacementById(@PathVariable("id") int id) {
        return iEmplacementService.getEmplacementById(id);
    }


    @DeleteMapping("/deleteEmplacement/{id}")
    private void deleteEmplacement(@PathVariable int id) {
        iEmplacementService.deleteEmplacement(id);
    }

    @PutMapping("/updateEmplacement/{id}")
    private Emplacement updateEmplacement(@RequestBody Emplacement Emplacement,@PathVariable int id) {
        iEmplacementService.updateEmplacement(Emplacement,id);
        return Emplacement;
    }




    /**************************************************************************************************/
    @GetMapping("/AllAdresseExpeditions")
    @ResponseBody
    public List<AdresseExpedition> getAllAllAdresseExpeditions() {
        return iAdresseExpeditionService.getAllAdresseExpeditions();
    }

    @PostMapping("/addAdresseExpedition")
    @ResponseBody
    public AdresseExpedition addAdresseExpedition(@RequestBody AdresseExpedition AdresseExpedition) {
        return iAdresseExpeditionService.addAdresseExpedition(AdresseExpedition);
    }

    @GetMapping("/getAdresseExpeditionById/{id}")
    @ResponseBody
    public AdresseExpedition getaddAdresseExpeditionById(@PathVariable("id") int id) {
        return iAdresseExpeditionService.getAdresseExpeditionById(id);
    }


    @DeleteMapping("/deleteAdresseExpedition/{id}")
    private void deletegetaddAdresseExpedition(@PathVariable("id") int id) {
        iAdresseExpeditionService.deleteAdresseExpedition(id);
    }

    @PutMapping("/updateAdresseExpedition")
    private AdresseExpedition updateAdresseExpedition(@RequestBody AdresseExpedition AdresseExpedition) {
        iAdresseExpeditionService.updateAdresseExpedition(AdresseExpedition);
        return AdresseExpedition;
    }

    @GetMapping("/AllAdresseDepartements")
    @ResponseBody
    public List<AdresseDepartement> getAllAllAdresseDepartements() {
        return iAdresseDepartementService.getAllAdresseDepartements();
    }

    @PostMapping("/addAdresseDepartement")
    @ResponseBody
    public AdresseDepartement addAdresseDepartement(@RequestBody AdresseDepartement AdresseDepartement) {
        return iAdresseDepartementService.addAdresseDepartement(AdresseDepartement);
    }

    @GetMapping("/getAdresseDepartementById/{id}")
    @ResponseBody
    public AdresseDepartement getaddAdresseDepartementById(@PathVariable("id") int id) {
        return iAdresseDepartementService.getAdresseDepartementById(id);
    }


    @DeleteMapping("/deleteAdresseDepartement/{id}")
    private void deletegetaddAdresseDepartement(@PathVariable("id") int id) {
        iAdresseDepartementService.deleteAdresseDepartement(id);
    }

    @PutMapping("/updateAdresseDepartement")
    private AdresseDepartement updateAdresseDepartement(@RequestBody AdresseDepartement AdresseDepartement) {
        iAdresseDepartementService.updateAdresseDepartement(AdresseDepartement);
        return AdresseDepartement;
    }
    //******************
    @GetMapping("/getDepartementParNom/{nomDepartement}")
    Departement getByNomDepartement(@PathVariable String nomDepartement) {
        return iDepartementService.getByNomDepartement(nomDepartement);
    }
    @PutMapping("/affecterDeparToAdrDepar/{idD}/{idAD}")
    public void affecterAdresseDepartementToDepartement(@PathVariable Integer idD,@PathVariable Integer idAD) {
        iDepartementService.affecterAdresseDepartementToDepartement(idD, idAD);
    }
    @GetMapping("getDepartByNomDepart/{emplacementDepartement}")
    public Departement getDepartByNomDepart(@PathVariable String emplacementDepartement) {
        return iDepartementService.getByNomDepartement(emplacementDepartement);

    }

    @PutMapping("/updateComplaint/{id}")
    public void updateComplaints(@RequestBody Complaints complaints,@PathVariable Long id ) {
        complaintsService.updateComplaints(complaints , id);
    }



    @DeleteMapping("/deleteComplaint/{id}")
    public void deleteComplaints(@PathVariable Long id) {

        complaintsService.deleteComplaints(id);

    }
    @GetMapping("/getallComplaint")
    public List<Complaints> getAll(){
        return complaintsService.getAll();
    }

    @GetMapping("/getComplaint/{id}")
    public Complaints findById(@PathVariable Long id) {
        return complaintsService.findById(id);
    }
    @GetMapping("/nbreComplaint")
    public int NmbreComplaints() {
        return complaintsService.NmbreComplaints()	;}
    @PostMapping("/addComplaint/{id}")
    public	String AddComplaints(@RequestBody Complaints com,@PathVariable Integer id){
        List<String> dic = complaintsRepository.Dictionnaire();
        for (int i = 1; i <= dic.size(); i++) {
            if (com.getTopic().contains(dic.get(i - 1))) {
                break;
            } else {
                if (i == dic.size()) {
                    complaintsService.AddComplaints(id, com);
                    SimpleMailMessage message = new SimpleMailMessage();

                    message.setTo(com.getUser().getEmail());
                    message.setSubject("Your complaint has been successfully added");
                    message.setText("Hello, Your complaint has been successfully added. Thank you for your feedback!");

                    // Send Message!
                    this.emailSender.send(message);

                    return "Email Sent by me!";

                }
            }

        }
        return "can not add complaints which contains a forbidden word";


    }

    @GetMapping("/affecter/{idR}/{idU}")
    public Complaints affecterUserAComplaints(@PathVariable Long idR,@PathVariable Integer idU) {
        return complaintsService.affecterUserAComplaints(idR, idU);
    }
    @GetMapping("/getAllEmplacementTrie")
    public List<Emplacement> findAllEmplacementTrie(){
        return iEmplacementService.findAllEmplacementTrié();
    }
    @GetMapping("/FiltreAdressExpedition/{pays}/{cite}")
    public List<AdresseExpedition> filterByPaysAndCite(@PathVariable String pays,@PathVariable String cite){
        return iAdresseExpeditionService.filterByPaysAndCite(pays, cite);
    }
    @GetMapping("/ChercherParNomDepartement")
    public List<Departement> ChercherParNomDepartementContainingIgnoreCase(@PathParam("nomDepartement") String nomDepartement){
        return iDepartementService.ChercherParNomDepartementContainingIgnoreCase(nomDepartement);
    }
    @PutMapping("/archiverComplaint/{id}")
    public void archiverComplaint(@PathVariable Long id) {
        complaintsService.archiverComplaint(id);
    }

    @DeleteMapping("/deleteEmplacementsNonAffectes")
    public void supprimerEmplacementsNonAffectes() {
        iEmplacementService.supprimerEmplacementsNonAffectes();
    }



}
