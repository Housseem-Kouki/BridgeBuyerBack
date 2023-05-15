package com.example.appeloffreservice.Controller;


import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.Offre;
import com.example.appeloffreservice.Entities.User;
import com.example.appeloffreservice.Repository.DevisFourniseurRepository;
import com.example.appeloffreservice.Repository.UserRepository;
import com.example.appeloffreservice.Services.AppelOffre.IAppeloffreService;
import com.example.appeloffreservice.Services.AppelOffre.IDevisFournisseurService;
import com.example.appeloffreservice.Services.AppelOffre.IOffreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AppeloffreController {

    IDevisFournisseurService iDevisFournisseurService;
    UserRepository userRepository;
    DevisFourniseurRepository devisFourniseurRepository;
    IAppeloffreService iAppeloffreService;
    IOffreService iOffreService;
    @GetMapping("/currentUser")
    public User profile(Principal principal){
        //Principal utilsateur username
        return userRepository.findByEmail(principal.getName());

    }


    @PostMapping("/addDevisAndassignAppelAndUserToDevis/{idAppeloffre}")
    @ResponseBody
    public DevisFourniseur addAndassignAppelAndUserToDevis(@RequestBody DevisFourniseur devisFourniseur
            , @PathVariable("idAppeloffre")int idAppeloffre
            , Principal principal){

        return   iDevisFournisseurService.addAndassignAppelAndUserToDevis(devisFourniseur,idAppeloffre,principal);
    }
    @PostMapping("/ajouterdevispourappel/{idAppeloffre}/{idUser}")
    @ResponseBody
    public DevisFourniseur addDevisAndassignAppelToDevis(@RequestBody DevisFourniseur devisFourniseur
            , @PathVariable("idAppeloffre")int idAppeloffre , @PathVariable("idUser")int idUser
    ){

        return   iDevisFournisseurService.addDevisAndassignAppelToDevis(devisFourniseur,idAppeloffre , idUser);
    }

    @PostMapping("/addAndAssignAppelToDemande/{iddemandeachat}")
    @ResponseBody
    public AppelOffre addAndAssignAppelToDemande (@RequestBody AppelOffre appelOffre, @PathVariable("iddemandeachat") int iddemandeachat){
        return iAppeloffreService.addAndAssignAppelToDemande(appelOffre,iddemandeachat);
    }
    @PostMapping("/addAndAssignOffreToAppel/{idA}")
    @ResponseBody
    public Offre addAndAssignOffreToAppel (@RequestBody Offre offre , @PathVariable("idA") int idA){
        return iOffreService.addAndAssignOffreToAppel(offre, idA);
    }
    @GetMapping("/AllAppelOffre")
    @ResponseBody
    public List<AppelOffre> getAllAppelOffre (){
        return iAppeloffreService.getAllAppelOffre();
    }
    @GetMapping("/getAppelOffreById/{id}")
    @ResponseBody
    public AppelOffre  getAppelOffreById(@PathVariable("id") int id){
        return   iAppeloffreService.getAppelOffrebyId(id);
    }
    @GetMapping("/getAppelOffreByEtat/{etat}")
    @ResponseBody
    public List<AppelOffre> getAppelOffreByEtat(@PathVariable("etat") int etat) {
        return iAppeloffreService.getAppelOffreByEtat(etat);
    }

    @DeleteMapping("/deleteappel/{id}")
    public void deleteAppelOffre(@PathVariable("id") int id){

        iAppeloffreService.deleteAppelOffre(id);
    }

    @PutMapping("/updateAppeloffre")
    private AppelOffre updateAppelOffre(@RequestBody AppelOffre appelOffre)
    {
        return iAppeloffreService.updateAppelOffre(appelOffre);

    }
/*
    @GetMapping("/searchA")
    public ResponseEntity<List<AppelOffre>> searchA(@RequestParam(required = false) String reference,
                                                    @RequestParam(required = false) String commentaire,
                                                    @RequestParam(required = false) double prixInitiale) {
        List<AppelOffre> appelOffres = iAppeloffreService.findByCriteria(reference, commentaire, prixInitiale);
        return ResponseEntity.ok(appelOffres);
    }
    @Autowired
    public  AppeloffreController (IAppeloffreService iAppeloffreService) {
        this.iAppeloffreService = iAppeloffreService;
    }
*/

    @GetMapping("/pdf/generate/{idAppel}")
    public void generatePdf(HttpServletResponse response , @PathVariable("idAppel") int idAppel) throws IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDate= dateFormat.format(new Date());
        String headerkey="Content-Disposition";
        String headerValue="attachement; filename=pdf_"+ currentDate + ".pdf";
        response.setHeader(headerkey, headerValue);
        this.iAppeloffreService.export(response,idAppel);
    }
    ///


 /*   @PostMapping("/addDevisFourniseur")
    @ResponseBody
    public DevisFourniseur addDevisFourniseur (@RequestBody DevisFourniseur devisFourniseur){

        return iDevisFournisseurService.addDevisFourniseur(devisFourniseur);
    }
  */

    @GetMapping("/AllDevisFourniseur")
    public List<DevisFourniseur> getAllDevisFourniseur (){

        return iDevisFournisseurService.getAllDevisFourniseur();
    }
    @GetMapping("/getdevisByidAppel/{idAppeloffre}")
    public List<DevisFourniseur> getDevisfournisseurByIDAppel (@PathVariable("idAppeloffre") int idAppeloffre){

        return iDevisFournisseurService.getDevisfournisseurByIDAppel(idAppeloffre);
    }
    @GetMapping("/calculremise/{id1}/{idA}/{pourcentageRemise}")
    public float calculremise(@PathVariable("id1") int id1 ,@PathVariable("idA") int idA ,@PathVariable("pourcentageRemise") float pourcentageRemise ) {
        return  iDevisFournisseurService.calculremise(id1,idA ,pourcentageRemise);
    }

    @GetMapping("/getDevisFournisseur/{idFourniseur}")
    public List<DevisFourniseur> getDevisFournisseur(@PathVariable("idFourniseur") int idFournisseur) {
        return iDevisFournisseurService.getDevisFournisseur(idFournisseur);
    }
    @DeleteMapping("/deleteDevisFourniseur/{id}")
    public void deleteDevisFourniseur(@PathVariable("id") int id){

        iDevisFournisseurService.deleteDevisFourniseur(id);
    }
    @GetMapping("/AllOffre")
    @ResponseBody
    public List<Offre> getAllOffre (){
        return iOffreService.getAllOffre();
    }
    @GetMapping("/getOffrebyid/{id}")
    @ResponseBody
    public Offre  getOffreById(@PathVariable("id") int id){
        return   iOffreService.getOffrebyId(id);
    }

    @GetMapping("/getOffreByEtat/{etat}")
    @ResponseBody
    public List<Offre> getOffreByEtat(@PathVariable("etat") int etat) {
        return iOffreService.getOffreByEtat(etat);
    }
    @PostMapping("/addoffre/{idDevisFourniseur}")
    @ResponseBody
    public Offre addoffre(@RequestBody Offre offre , @PathVariable("idDevisFourniseur") int idDevisFourniseur){
        return iOffreService.addoffre(offre,idDevisFourniseur);

    }

}
