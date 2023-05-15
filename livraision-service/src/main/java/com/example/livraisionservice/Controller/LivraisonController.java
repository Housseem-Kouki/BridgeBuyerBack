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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class LivraisonController {

    ILivraisonService iLivraisonService;
    IFactureAvoirService iFactureAvoirService;
    IBonRetourService iBonRetourService;
    IBonReceptionService iBonReceptionService;

    //*********************  Livraison  *********************//



    @PostMapping("/addAndAssignToCommande/{idCommande}") //fonctionnelle
    @ResponseBody
    public Livraison addAndAssignToCommande(@PathVariable  int idCommande, @RequestBody Livraison l){
        return iLivraisonService.addAndAssignToCommande(idCommande,l);
    }


    @GetMapping("/AllLivraison") //fonctionnelle
    @ResponseBody
    public List<Livraison> getAllLivraisonList() {
        return iLivraisonService.getAllLivraison();
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


    @DeleteMapping("/delete/{idLivraison}") //fonctionnelle
    public void deleteLivraison(@PathVariable("idLivraison") int idLivraison) {
        iLivraisonService.deleteLivraison(idLivraison);
    }
    @PutMapping("/restaure/{id}") //fonctionnel
    public void restoreLivraison(@PathVariable int id) {
        iLivraisonService.restoreLivraison(id);
    }

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


     /*   @PutMapping("/updateBonLivraisonFournisseur/{idLivraison}/{qteRecue}/{qteRetour}") //fonctionnelle
        public void modifierQuantiteLivraison(@PathVariable("idLivraison") int idLivraison,@PathVariable("qteRecue") Integer qteRecue,@PathVariable("qteRetour") Integer qteRetour){
                iLivraisonService.modifierQuantiteLivraison(idLivraison, qteRecue ,qteRetour);
        }*/

    @PutMapping("/valideeBL/{idLivraison}") //fonctionnelle
    public Livraison balideeBL(@PathVariable("idLivraison") int idLivraison){
        return iLivraisonService.validerBL(idLivraison) ;
    }

    //recherche avancée
    @GetMapping("/search") //fonctionnel
    public ResponseEntity<List<Livraison>> search(@RequestParam(required = false) Integer quantiteDelivre,
                                                  @RequestParam(required = false) Date dateLivraison,
                                                  @RequestParam(required = false) String etat) {
        List<Livraison> livraisons = iLivraisonService.rechercheAvance(quantiteDelivre, dateLivraison, etat);
        return ResponseEntity.ok(livraisons);
    }
    /*@GetMapping("/SearchMultipleLivraison/{key}")
    @ResponseBody
    public List<Livraison> SearchMultiple(@PathVariable("key") String key) {
        return iLivraisonService.SearchMultiple(key);
    }*/


    //*********************  Bon Reception  *********************//

    @GetMapping("/AllBonReception")
    @ResponseBody
    public List<BonReception> getAllBonReception() {
        return iBonReceptionService.getAllBonReception();
    }

    @PostMapping("/addBonReception/{idLivraison}")
    @ResponseBody
    public BonReception addBonReception(@PathVariable("idLivraison") int idLivraison,@RequestBody BonReception b) {
        return iBonReceptionService.addBonReception(idLivraison, b);
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

    @PutMapping("restaureBR/{id}")
    public void restoreBonReception(@PathVariable int id) {
        iBonReceptionService.restoreBonReception(id);
    }
    @PutMapping("/updateEtat")
    public BonReception updateEtat(@RequestBody BonReception br) {
        return iBonReceptionService.updateEtat(br);

    }
    @GetMapping("/getBonReceptByUser/{id}")
    public List<BonReception> getBonReceptByUser(@PathVariable("id") int id){
        return iBonReceptionService.getBonReceptByUser(id);
    }
      /*  @GetMapping("/SearchMultipleBonRecept/{keyword}")
        @ResponseBody
        public List<BonReception> SearchMultiple1(@PathVariable("keyword") String key) {
                return iBonReceptionService.SearchMultiple1(key);
        }*/


    //recherche avancée
    @GetMapping("/search1")
    public ResponseEntity<List<BonReception>> search1(@RequestParam(required = false) Date dateReception,
                                                      @RequestParam(required = false) Integer quantiteRecevoir,
                                                      @RequestParam(required = false) Integer quantiteAccepte,
                                                      @RequestParam(required = false) String etat) {
        List<BonReception> bonReceptions = iBonReceptionService.rechercheAvance1(dateReception,quantiteRecevoir, quantiteAccepte, etat);
        return ResponseEntity.ok(bonReceptions);
    }

    //*********************  Bon Retour  *********************//

    @PostMapping("/addBonRetour/{idBonReception}") //fonctionnel
    @ResponseBody
    public BonRetour addBonRetour(@RequestBody BonRetour br,@PathVariable("idBonReception") int idBonReception) {
        return iBonRetourService.addBonRetour(br,idBonReception);
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

    @PutMapping("restaureBRT/{id}")
    public void restoreBonRetour(@PathVariable int id) {
        iBonRetourService.restoreBonRetour(id);
    }

    @GetMapping("/SearchMultipleBonRetour/{keyword}")
    @ResponseBody
    public List<BonRetour> SearchMultiple2(@PathVariable("keyword") String key) {
        return iBonRetourService.SearchMultiple2(key);
    }

    @PutMapping("/affecterFactureAvoirToBonRetour/{idBonRetour}") //fonctionnel
    public FactureAvoir affecterFactureAvoirToBonRetour(  @RequestBody FactureAvoir f ,@PathVariable("idBonRetour") int idBonRetour) {
        return iBonRetourService.affecterFactureAvoirToBonRetour(f, idBonRetour);
    }
    //*********************  Facture Avoir  *********************//

    @PostMapping("/addFactureAvoir")
    @ResponseBody
    public FactureAvoir addFactureAvoir(@RequestBody FactureAvoir f,@PathVariable("idBonRetour") int idBonRetour) {
        return iFactureAvoirService.addFactureAvoir(f,idBonRetour);
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

    @PutMapping("/restaureFA/{id}")
    public void restoreFactureAvoir(@PathVariable int id) {iFactureAvoirService.restoreFactureAvoir(id);
    }

    @GetMapping("/SearchMultipleFactureAvoir/{keyword}")
    @ResponseBody
    public List<FactureAvoir> SearchMultiple3(@PathVariable("keyword") String key) {
        return iFactureAvoirService.SearchMultiple3(key);
    }

    //pdf
    @GetMapping("/pdf/generate/{idLivraison}")
    public void generatePdf(HttpServletResponse response , @PathVariable("idLivraison") int idLivraison) throws IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDate= dateFormat.format(new Date());
        String headerkey="Content-Disposition";
        String headerValue="attachement; filename=pdf_"+ currentDate + ".pdf";
        response.setHeader(headerkey, headerValue);
        this.iLivraisonService.export(response,idLivraison);
    }



}
