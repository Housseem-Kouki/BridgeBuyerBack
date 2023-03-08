package com.example.emlacementservice.Controller;


import com.example.emlacementservice.Entities.*;
import com.example.emlacementservice.Repository.EmplacementRepository;
import com.example.emlacementservice.Repository.ReclamationRepository;
import com.example.emlacementservice.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    IDepartementService iDepartementService;
    IDeviseService iDeviseService;
    IEmplacementService iEmplacementService;
    IAdresseDepartementService iAdresseDepartementService;
    IAdresseExpeditionService iAdresseExpeditionService;
    ReclamationService ReclamationService;
    ReclamationRepository reclamationRepository;

    JavaMailSender emailSender;
    private final EmplacementRepository emplacementRepository;


    @GetMapping("/AllDepartements")
    @ResponseBody
    public List<Departement> getAllDepartements() {
        return iDepartementService.getAllDepartements();
    }

    @PostMapping("/addDepartement")
    @ResponseBody
    public Departement addDepartement(@RequestBody Departement Departement) {
        return iDepartementService.addDepartement(Departement);
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

    @PostMapping("/addEmplacement")
    @ResponseBody
    public Emplacement addEmplacement(@RequestBody Emplacement Emplacement) {
        return iEmplacementService.addEmplacement(Emplacement);
    }

    @GetMapping("/getEmplacementById/{id}")
    @ResponseBody
    public Emplacement getEmplacementById(@PathVariable("id") int id) {
        return iEmplacementService.getEmplacementById(id);
    }


    @DeleteMapping("/deleteEmplacement/{id}")
    private void deleteEmplacement(@PathVariable("id") int id) {
        iEmplacementService.deleteEmplacement(id);
    }

    @PutMapping("/updateEmplacement")
    private Emplacement updateEmplacement(@RequestBody Emplacement Emplacement) {
        iEmplacementService.updateEmplacement(Emplacement);
        return Emplacement;
    }
    @PostMapping("affecterEmplacementToDepartement/{idD}")
    public Emplacement affecterEmplacementToDepartement(@RequestBody Emplacement e ,  @PathVariable("idD")Integer idD){
        return iEmplacementService.affecterEmplacementToDepartement(e,idD);
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

    @PostMapping("/affecterAdresseToEmpl/{ide}")
    public AdresseExpedition affecterAdresseExpeditionToEmplacement(@RequestBody AdresseExpedition ad, @PathVariable Integer ide) {
        iAdresseExpeditionService.affecterShippingAdresseToEmplac(ad, ide);
        return ad ;
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
    private void deleteAdresseDepartement(@PathVariable("id") int id) {
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

    @PostMapping("/affecterAdresseDepartement/{idD}")
    public AdresseDepartement affecterAdresseDepartementToDepartement(@RequestBody AdresseDepartement ad, @PathVariable Integer idD) {
        return iAdresseDepartementService.affecterAdresseDepartementToDepartement(ad, idD);
    }

    @GetMapping("getDepartByNomDepart/{emplacementDepartement}")
    public Departement getDepartByNomDepart(@PathVariable String emplacementDepartement) {
        return iDepartementService.getByNomDepartement(emplacementDepartement);

    }

    @PutMapping("/update")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation) {
        return ReclamationService.updateReclamation(reclamation);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteReclamation(@PathVariable Long id) {

        ReclamationService.deleteReclamation(id);

    }

    @GetMapping("/all")
    public List<Reclamation> getAll() {
        return ReclamationService.getAll();
    }

    @GetMapping("/get/{id}")
    public Reclamation findById(@PathVariable Long id) {
        return ReclamationService.findById(id);
    }

    @GetMapping("/nbre")
    public int NmbreReclamation() {
        return ReclamationService.NmbreReclamation();
    }

    @PostMapping("/add")
    public String AddReclamation( @RequestBody Reclamation com) {
		/*List<String> dic = reclamationRepository.Dictionnaire();
		for (int i = 1; i <= dic.size(); i++) {
			if (com.getTopic().contains(dic.get(i - 1))) {
				break;
			} else {
				if (i == dic.size()) { */
        ReclamationService.AddReclamation( com);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(com.getUser().getEmail());
        message.setSubject("Your complaint has been successfully added");
        message.setText("Hello, Your complaint has been successfully added. Thank you for your feedback!");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent by me!";

		/*		}
			}

		}
		return "can not add Reclamation which contains a forbidden word";*/

    }

    @GetMapping("/affecter/{idR}/{idU}")
    public Reclamation affecterUserAReclamation(@PathVariable Long idR, @PathVariable Integer idU) {
        return ReclamationService.affecterUserAReclamation(idR, idU);
    }

   @GetMapping("/getAllEmplacementTrie")
    public Page<Emplacement> findAllEmplacementTrie(Pageable pageable) {
        return iEmplacementService.findAllEmplacementTrié(pageable);
    }



    @GetMapping("/FiltreAdressExpedition/{pays}/{cite}")
    public List<AdresseExpedition> filterByPaysAndCite(@PathVariable String pays, @PathVariable String cite) {
        return iAdresseExpeditionService.filterByPaysAndCite(pays,cite);
    }

    @GetMapping("/ChercherParNomDepartement")
    public List<Departement> ChercherParNomDepartementContainingIgnoreCase(@PathParam("nomDepartement") String nomDepartement) {
        return iDepartementService.ChercherParNomDepartementContainingIgnoreCase(nomDepartement);
    }

    @PutMapping("/archiverReclamation/{id}")
    public void archiverReclamation(@PathVariable Long id) {
        ReclamationService.archiverReclamation(id);
    }

    @DeleteMapping("/deleteEmplacementsNonAffectes")
    public void supprimerEmplacementsNonAffectes() {
        iEmplacementService.supprimerEmplacementsNonAffectes();
    }

    /********************************/

    @GetMapping("/AllDevises")
    @ResponseBody
    public List<Devise> getAllAllDevises() {
        return iDeviseService.getAllDevises();
    }

    @PostMapping("/addDevise")
    @ResponseBody
    public Devise addDevise(@RequestBody Devise Devise) {
        return iDeviseService.addDevise(Devise);
    }

    @GetMapping("/getDeviseById/{id}")
    @ResponseBody
    public Devise getaddDeviseById(@PathVariable("id") int id) {
        return iDeviseService.getDeviseById(id);
    }


    @DeleteMapping("/deleteDevise/{id}")
    public void deletegetaddDevise(@PathVariable("id") int id) {
        iDeviseService.deleteDevise(id);
    }

    @PutMapping("/updateDevise")
    public Devise updateDevise(@RequestBody Devise Devise) {

        iDeviseService.updateDevise(Devise);
        return Devise;
    }

    @PutMapping("/activerDevise/{nomDev}")
    public void activerdevise(@PathVariable("nomDev") String nom) {
        iDeviseService.ActiverDevise(nom);
    }

    @PutMapping("/desactiverDevise/{nomDev}")
    public void desactiverdevise(@PathVariable("nomDev") String nom) {
        iDeviseService.DesactiverDevise(nom);
    }

    @PostMapping("/affecterdevise/{idE}")
    public Devise affecterDeviseToEmp(@RequestBody Devise d, @PathVariable Integer idE) {
       return iDeviseService.affecterDeviseToEmp(d, idE);


    }
   @GetMapping("/search")
    public ResponseEntity<List<Emplacement>> search(@RequestParam(required = false) String key1,
                                                    @RequestParam(required = false) String key2,
                                                    @RequestParam(required = false) String key3) {
        List<Emplacement> Emplacements = iEmplacementService.findByCriteria(key1,key2,key3);
        return ResponseEntity.ok(Emplacements);
    }
 /* @GetMapping("/search")
  public List<Emplacement> search(@RequestParam(required = false) String key) {
      return iEmplacementService.searchEmplacement(key);
  }*/
}




