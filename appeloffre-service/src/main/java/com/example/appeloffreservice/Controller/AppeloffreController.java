package com.example.appeloffreservice.Controller;


import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.Offre;
import com.example.appeloffreservice.Repository.AppelOffreRepository;
import com.example.appeloffreservice.Repository.DemandeAchatRepository;
import com.example.appeloffreservice.Repository.DevisFourniseurRepository;
import com.example.appeloffreservice.Services.AppelOffre.IAppeloffreService;
import com.example.appeloffreservice.Services.AppelOffre.IDevisFournisseurService;
import com.example.appeloffreservice.Services.AppelOffre.IOffreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class AppeloffreController {

    IAppeloffreService iAppeloffreService;
    IDevisFournisseurService iDevisFournisseurService;
    IOffreService iOffreService;
 AppelOffreRepository appelOffreRepository;
   DemandeAchatRepository demandeAchatRepository;
   DevisFourniseurRepository devisFourniseurRepository;

    @GetMapping("/hello")
    public String hello (){
        return"hiiiiiiiiiii" ;
    }

    @GetMapping("/AllAppelOffre")
    @ResponseBody
    public List<AppelOffre> getAllAppelOffre (){
        return iAppeloffreService.getAllAppelOffre();
    }

    @PostMapping("/addAndAssignAppelToDemande/{idD}")
    @ResponseBody
    public AppelOffre addAndAssignAppelToDemande (@RequestBody AppelOffre appelOffre, @PathVariable("idD") int idD){
        return iAppeloffreService.addAndAssignAppelToDemande(appelOffre,idD);
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



        @DeleteMapping("/deleteAppelOffre/{id}")
    private void deleteAppelOffre(@PathVariable("id") int id)
    {
        iAppeloffreService.deleteAppelOffre(id);
    }

    @PutMapping("/updateDemandeAchat")
    private AppelOffre updateAppelOffre(@RequestBody AppelOffre appelOffre)
    {
        return iAppeloffreService.updateAppelOffre(appelOffre);

    }


    @PostMapping("/assignofferToAppelOffre/{idAppeloffre}/{idOffer}")
    public void assignofferToAppelOffre(@PathVariable("idAppeloffre") int idAppeloffre,@PathVariable("idOffer") int idOffer){
        iOffreService.assignofferToAppelOffre(idAppeloffre,idOffer);
    }
    @GetMapping("/retrieveAppelOffer/{etat}")
    @ResponseBody
    public List<AppelOffre> retrieveAppelOffer(@PathVariable("etat") int etat) {
        return iAppeloffreService.retrieveAppelOffer(etat);
    }
    @GetMapping("/Search/{key}")
    @ResponseBody
    public List<AppelOffre> Search(@PathVariable("key") String key)
    {
        return iAppeloffreService.Search(key);
    }



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


        @GetMapping("/pdf/generate/{idAppel}")
        public void generatePdf(HttpServletResponse response ,@PathVariable("idAppel") int idAppel) throws IOException {

        response.setContentType("application/pdf");
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
            String currentDate= dateFormat.format(new Date());
            String headerkey="Content-Disposition";
            String headerValue="attachement; filename=pdf_"+ currentDate + ".pdf";
            response.setHeader(headerkey, headerValue);
            this.iAppeloffreService.export(response,idAppel);
        }


    /////////////////////////
    @GetMapping("/AllDevisFourniseur")
    @ResponseBody
    public List<DevisFourniseur> getAllDevisFourniseur (){
        return iDevisFournisseurService.getAllDevisFourniseur();
    }

    @PostMapping("/addDevisFourniseur")
    @ResponseBody
    public DevisFourniseur addDevisFourniseur (@RequestBody DevisFourniseur devisFourniseur){

        return iDevisFournisseurService.addDevisFourniseur(devisFourniseur);
    }

    @GetMapping("/getDevisFourniseurById/{id}")
    @ResponseBody
    public DevisFourniseur  getDevisFourniseurById(@PathVariable("id") int id){
        return   iDevisFournisseurService.getDevisFourniseurbyId(id);
    }


    @DeleteMapping("/deleteDevisFourniseur/{id}")
    private void deleteDevisFourniseur(@PathVariable("id") int id)
    {
        iDevisFournisseurService.deleteDevisFourniseur(id);
    }

    @PutMapping("/updateDevisFourniseur")
    @RequestMapping
    private DevisFourniseur updateDevisFourniseur(@RequestBody DevisFourniseur devisFourniseur)
    {
        return iDevisFournisseurService.updateDevisFourniseur(devisFourniseur);


    }

    @PostMapping("/assignAppeloffreAndUserToDevis/{idAppeloffre}/{idDevis}/{idUser}")
    public void assignAppeloffreAndUserToDevis(@PathVariable("idAppeloffre")int idAppeloffre,@PathVariable("idDevis") int idDevis , @PathVariable("idUser") int idUser){
        iDevisFournisseurService.assignAppeloffreAndUserToDevis(idAppeloffre,idDevis,idUser);
    }

    @GetMapping("/comparerDevis/{id1}/{id2}")
    public DevisFourniseur comparerDevis(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        return iDevisFournisseurService.comparerDevis(id1, id2);
    }
   /* @GetMapping("/calculremise/{id1}")
    public double calculremise(@PathVariable("id1") int id1) {
        return iDevisFournisseurService.calculremise(id1);
    }*/

 /*   @GetMapping("comparerListDevis")
    public DevisFourniseur comparerListDevis(@RequestBody) {
        return iDevisFournisseurService.comparerListDevis(id1, id2);
    }*/
      @GetMapping("/calculremise/{id1}/{idA}/{pourcentageRemise}")
    public float calculremise(@PathVariable("id1") int id1 ,@PathVariable("idA") int idA ,@PathVariable("pourcentageRemise") float pourcentageRemise ) {
       return  iDevisFournisseurService.calculremise(id1,idA ,pourcentageRemise);
    }

    @GetMapping("/getDevisFournisseur/{idFourniseur}")
    public List<DevisFourniseur> getDevisFournisseur(@PathVariable("idFourniseur") int idFournisseur) {
        return iDevisFournisseurService.getDevisFournisseur(idFournisseur);
    }

    @GetMapping("/updateTotalDevis/{idDevis}")
    public DevisFourniseur updateTotalDevis(@PathVariable("idDevis") int idDevis){
          return iDevisFournisseurService.updateTotalDevis(idDevis);
    }

    ////////////////
    @GetMapping("/AllOffre")
    @ResponseBody
    public List<Offre> getAllOffre (){
        return iOffreService.getAllOffre();
    }

    @PostMapping("/addAndAssignOffreToAppel/{idA}")
    @ResponseBody
    public Offre addAndAssignOffreToAppel (@RequestBody Offre offre , @PathVariable("idA") int idA){
        return iOffreService.addAndAssignOffreToAppel(offre, idA);
    }

    @GetMapping("/getOffrebyid/{id}")
    @ResponseBody
    public Offre  getOffreById(@PathVariable("id") int id){
        return   iOffreService.getOffrebyId(id);
    }


    @DeleteMapping("/deleteOffre/{id}")
    private void deleteOffre(@PathVariable("id") int id)
    {
        iOffreService.deleteOffre(id);
    }

    @PutMapping("/updateDevisFourniseur")
    private Offre updateOffre(@RequestBody Offre offre)
    {
        return iOffreService.updateOffre(offre);

    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Offre>> recherche(@RequestParam(required = false) String commentaire,
                                                    @RequestParam(required = false) double prixOffre) {
        List<Offre> offres = iOffreService.findByCriteria( commentaire, prixOffre);
        return ResponseEntity.ok(offres);
    }
    @GetMapping("/getOffreByEtat/{etat}")
    @ResponseBody
    public List<Offre> getOffreByEtat(@PathVariable("etat") int etat) {
        return iOffreService.getOffreByEtat(etat);
    }
/////////////////////
  /*  @GetMapping("/appel-offre/{id}/pdf")
    public ResponseEntity<byte[]> afficherAppelOffreEnPDF(@PathVariable("id") int id) throws DocumentException {
        // Récupération des informations de l'appel d'offre depuis votre source de données (base de données, etc.)
        AppelOffre appelOffre = iAppeloffreService.getAppelOffrebyId(id);

        // Création d'un document PDF vide
        Document document = new Document();

        // Configuration de la sortie pour envoyer le PDF en bytes
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        // Ouverture du document PDF
        document.open();

        // Ajout des détails de l'appel d'offre au document PDF
        document.add(new Paragraph("Détails de l'appel d'offre"));
        document.add(new Paragraph("Titre: " + appelOffre.getTitre()));
        document.add(new Paragraph("Description: " + appelOffre.getDescription()));
        document.add(new Paragraph("Date de début: " + appelOffre.getDateDebut()));
        document.add(new Paragraph("Date de fin: " + appelOffre.getDateFin()));

        // Fermeture du document PDF
        document.close();

        // Renvoi du PDF en tant que tableau de bytes avec un header adapté pour indiquer qu'il s'agit d'un fichier PDF
        byte[] pdfBytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("details-appel-offre.pdf").build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}*/

}
