package com.example.livraisionservice.Controller;

import com.example.livraisionservice.Entities.BonReception;
import com.example.livraisionservice.Entities.BonRetour;
import com.example.livraisionservice.Entities.FactureAvoir;
import com.example.livraisionservice.Entities.Livraison;
import com.example.livraisionservice.Repository.LivraisonRepository;
import com.example.livraisionservice.Service.BonReception.IBonReceptionService;
import com.example.livraisionservice.Service.BonRetour.IBonRetourService;
import com.example.livraisionservice.Service.FactureAvoir.IFactureAvoirService;
import com.example.livraisionservice.Service.ILivraisonService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class LivraisonController {

    ILivraisonService iLivraisonService;
    IFactureAvoirService iFactureAvoirService;
    IBonRetourService iBonRetourService;
    IBonReceptionService iBonReceptionService;
    //LivraisonRepository livraisonRepository;

    //*********************  Livraison  *********************//
    @GetMapping("/AllLivraison") //fonctionnelle
    @ResponseBody
    public List<Livraison> getAllLivraisonList() {
        return iLivraisonService.getAllLivraison();
    }

    @PostMapping("/addLivraison") //fonctionnelle
    @ResponseBody
    public Livraison addLivraison(@RequestBody Livraison l) {
        return iLivraisonService.addLivraison(l);
    }

    @PostMapping("/addAndAssignToCommande/{idCommande}") //fonctionnelle
    @ResponseBody
    public Livraison addAndAssignToCommande(@PathVariable  int idCommande, @RequestBody Livraison l){
        return iLivraisonService.addAndAssignToCommande(idCommande,l);
    }
    @GetMapping("/getLivraisonById/{id}") //fonctionnelle
    @ResponseBody
    public Livraison getLivraisonById(@PathVariable("id") int id) {
        return iLivraisonService.getLivraisonById(id);
    }

    @GetMapping("/getAlllivraisonByAcheteur/{id}") //fonctionnelle
    @ResponseBody
    public List<Livraison> getAlllivraisonByuser(@PathVariable("id") int id) {
        return iLivraisonService.getAllLivraisonUserContecter(id);
    }

    @GetMapping("/getAlllivraisonByFournisseur/{id}") //fonctionnelle
    @ResponseBody
    public List<Livraison> getAlllivraisonByFournisseur(@PathVariable("id") int id) {
        return iLivraisonService.getLivraisonByFournisseur(id);
    }

    @PutMapping("/affecterBonReceptToBonLivr/{idBonReception}/{idLivraison}") //fonctionnelle
    public Livraison affecterBonReceptToBonLivr(@PathVariable("idBonReception") int idBonReception, @PathVariable("idLivraison") int idLivraision) {
        return iLivraisonService.affecterBonReceptToBonLivr(idBonReception, idLivraision);
    }

    @GetMapping("/SearchMultiple/{keyword}") //fonctionnelle
    @ResponseBody
    public List<Livraison> SearchMultiple(@PathVariable("keyword") String key) {
        return iLivraisonService.SearchMultiple(key);
    }

    @DeleteMapping("/delete/{idLivraison}") //fonctionnelle
    public void deleteLivraison(@PathVariable("idLivraison") int idLivraison) {
        iLivraisonService.deleteLivraison(idLivraison);
    }

      /*  @GetMapping("/getCommandeByLivraison/{idCommande}")
        public List<Livraison> getCommandeByLivraison(@PathVariable("idCommande") int idCommande) {
                return iLivraisonService.getLivraisonByCommande(idCommande);
        }*/


    @PutMapping("/updateDate/{idLivraison}") //fonctionnelle
    public ResponseEntity<Livraison> updateLivraisonDate(@PathVariable("idLivraison") int idLivraison, @RequestParam("dateLivraison") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateLivraison) {
        Livraison livraison = iLivraisonService.updateLivraisonDate(idLivraison, dateLivraison);
        return new ResponseEntity<>(livraison, HttpStatus.OK);
    }

    //Tri
    @GetMapping("/sort-by-quantity") //fonctionnelle
    public List<Livraison> sortByQuantity() {
        return iLivraisonService.sortLivraisonsByQuantity();
    }


    @PutMapping("/updateBonLivraisonFournisseur/{idLivraison}/{qteRecue}/{qteRetour}") //fonctionnelle
    public void modifierQuantiteLivraison(@PathVariable("idLivraison") int idLivraison,@PathVariable("qteRecue") Integer qteRecue,@PathVariable("qteRetour") Integer qteRetour){
        iLivraisonService.modifierQuantiteLivraison(idLivraison, qteRecue ,qteRetour);
    }

    @PutMapping("/valideeBL/{idLivraison}") //fonctionnelle
    public Livraison balideeBL(@PathVariable("idLivraison") int idLivraison){
        return iLivraisonService.validerBL(idLivraison) ;
    }

    //*********************  Bon Reception  *********************//

    @GetMapping("/AllBonReception")
    @ResponseBody
    public List<BonReception> getAllBonReception() {
        return iBonReceptionService.getAllBonReception();
    }

    @PostMapping("/addBonReception")
    @ResponseBody
    public BonReception addBonReception(@PathVariable("idBonReception") int idBonReception,@PathVariable("qteAccept")int qteAccept,@PathVariable("qteRefus")int qteRefus) {
        return iBonReceptionService.addBonReception(idBonReception,qteAccept,qteRefus);
    }

    @GetMapping("/getBonReceptionById/{id}")
    @ResponseBody
    public BonReception getBonReceptionById(@PathVariable("id") int id) {
        return iBonReceptionService.getBonReceptionById(id);
    }


    @DeleteMapping("/deleteBonReception/{id}")
    private void deleteBonReception(@PathVariable("id") int id) {
        iBonReceptionService.deleteBonReception(id);
    }

    @PutMapping("/updateEtat")
    public BonReception updateEtat(@RequestBody BonReception br) {
        return iBonReceptionService.updateEtat(br);

    }
    @GetMapping("/getBonReceptByUser/{id}")
    public List<BonReception> getBonReceptByUser(@PathVariable("id") int id){
        return iBonReceptionService.getBonReceptByUser(id);
    }
    @GetMapping("/SearchMultipleBonRecept/{keyword}")
    @ResponseBody
    public List<BonReception> SearchMultiple1(@PathVariable("keyword") String key) {
        return iBonReceptionService.SearchMultiple1(key);
    }

    @PutMapping("/affecterBonRetourToBonRecept/{idBonReception}/{idBonRetour}")
    public BonReception affecterBonRetourToBonRecept(@PathVariable("idBonReception") int idBonReception, @PathVariable("idBonRetour") int idBonRetour) {
        return iBonReceptionService.affecterBonRetourToBonRecept(idBonReception, idBonRetour);
    }

    //*********************  Bon Retour  *********************//
    @PostMapping("/addBonRetour/{idBonReception}/{qnt}")
    @ResponseBody
    public BonRetour addBonRetour(@RequestBody BonRetour br,@PathVariable("idBonReception") int idBonReception, @PathVariable("qnt")int qnt) {
        return iBonRetourService.addBonRetour(br,idBonReception,qnt);
    }

    @GetMapping("/AllBonRetour")
    @ResponseBody
    public List<BonRetour> getAllBonRetour() {
        return iBonRetourService.getAllBonRetour();
    }



    @GetMapping("/getBonRetourById/{id}")
    @ResponseBody
    public BonRetour getBonRetourById(@PathVariable("id") int id) {
        return iBonRetourService.getBonRetourById(id);
    }


    @DeleteMapping("/deleteBonRetour/{id}")
    private void deleteBonRetour(@PathVariable("id") int id) {
        iBonRetourService.deleteBonRetour(id);
    }



    @GetMapping("/SearchMultipleBonRetour/{keyword}")
    @ResponseBody
    public List<BonRetour> SearchMultiple2(@PathVariable("keyword") String key) {
        return iBonRetourService.SearchMultiple2(key);
    }

    @PutMapping("/affecterFactureAvoirToBonRetour/{idFactureAvoir}/{idBonRetour}") //fonctionnel
    public BonRetour affecterFactureAvoirToBonRetour(@PathVariable("idFactureAvoir") int idFactureAvoir, @PathVariable("idBonRetour") int idBonRetour) {
        return iBonRetourService.affecterFactureAvoirToBonRetour(idFactureAvoir, idBonRetour);
    }

    //*********************  Facture Avoir  *********************//

    @PostMapping("/addFactureAvoir")
    @ResponseBody
    public FactureAvoir addFactureAvoir(@RequestBody FactureAvoir f) {
        return iFactureAvoirService.addFactureAvoir(f);
    }

    @GetMapping("/AllFactureAvoir")
    @ResponseBody
    public List<FactureAvoir> getAllFactureAvoir() {
        return iFactureAvoirService.getAllFactureAvoir();
    }



    @DeleteMapping("/deleteFactureAvoir/{id}")
    private void deleteFactureAvoir(@PathVariable("id") int id) {
        iFactureAvoirService.deleteFactureAvoir(id);
    }



    @GetMapping("/SearchMultipleFactureAvoir/{keyword}")
    @ResponseBody
    public List<FactureAvoir> SearchMultiple3(@PathVariable("keyword") String key) {
        return iFactureAvoirService.SearchMultiple3(key);
    }


    //***********Upload file***************
    @PostMapping("/uploadFile") //prblm lors du test !!!!!!!!
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            // Vérifier que le fichier n'est pas vide
            if (file.isEmpty()) {
                message = "Veuillez sélectionner un fichier à télécharger.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }

            // Traiter le fichier (par exemple, le stocker sur le disque dur)
            // ...

            message = "Le fichier " + file.getOriginalFilename() + " a été téléchargé avec succès.";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Une erreur s'est produite lors du téléchargement du fichier : " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }

    //PDF

}
