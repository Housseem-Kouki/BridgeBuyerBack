package com.example.demandeachatservice.Controller;


import com.example.demandeachatservice.Entities.*;
import com.example.demandeachatservice.Repository.CommentRepository;
import com.example.demandeachatservice.Services.DemandeAchat.Article.ArticlePdfExporter;
import com.example.demandeachatservice.Services.DemandeAchat.Article.ExportArticleExcel;
import com.example.demandeachatservice.Services.DemandeAchat.Article.IArticleService;
import com.example.demandeachatservice.Services.DemandeAchat.Comment.ICommentService;
import com.example.demandeachatservice.Services.DemandeAchat.DemandeAchatService;
import com.example.demandeachatservice.Services.DemandeAchat.IDemandeAchatService;
import com.example.demandeachatservice.Services.DemandeAchat.ISendEmailService;
import com.example.demandeachatservice.Services.DemandeAchat.NatureArticle.INatureArticleService;
import com.example.demandeachatservice.Services.DemandeAchat.Unite.IUniteService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RestController
@AllArgsConstructor

public class DemandeAchatController {
    IDemandeAchatService iDemandeAchatService ;
    IArticleService iArticleService ;
    INatureArticleService iNatureArticleService ;
    IUniteService iUniteService ;
    ICommentService iCommentService;

    ExportArticleExcel ExportArticleExcel ;
    DemandeAchatService demandeAchatService ;
    ISendEmailService iSendEmailService ;
    private final CommentRepository commentRepository;
    ArticlePdfExporter articlePdfExporter;


    //////////////////////////////////////////
/*   @GetMapping("/download/appointments.xlsx")
    public ResponseEntity<InputStreamResource> downloadCsv(HttpServletResponse response) throws IOException {
        List<Article> articles =(List<Article>) articleRepository.findAll() ;
        ByteArrayInputStream bais = ExportArticleExcel.articleExcelFile(articles) ;
       HttpHeaders headers = new HttpHeaders();
       headers.add("Content-Disposition" ,"inline ; filename=article.xlsx");
       return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais)) ;

    }*/
    @GetMapping(value = "/articles/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> articlePDFReport() throws Exception {
        ByteArrayInputStream bis = articlePdfExporter.generatePDFReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=articlesreport.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    /////////////////////////////////////////
    @GetMapping ("/AllDemandeAchat")
    @ResponseBody
    public List<DemandeAchat> getAllDemandeAchatList(){
        return iDemandeAchatService.getAllDemandeAchat();

    }

    @PostMapping("/addDemandeAchat")
    @ResponseBody
    public DemandeAchat addDemandeAchat (@RequestBody DemandeAchat d, Principal principal ){
        iSendEmailService.sendSimpleEmail("elkhater.elkharouf@esprit.tn","your Purshase Request is taken care of!","Purs" +
                "hase Request Response");
        return iDemandeAchatService.addDemandeAchat(d , principal );
    }

    @GetMapping("/getDemandeAchatById/{id}")
    @ResponseBody
    public DemandeAchat  getDemandeAchatById(@PathVariable("id") int id){
        return   iDemandeAchatService.getDemandeAchatById(id);
    }
    //----send mail--
    @GetMapping("/sendmail/{id}")
    public DemandeAchat sendmail(@PathVariable("id") int id){

        String email= demandeAchatService.getDemandeAchatById(id).getAcheteur().getEmail();

        demandeAchatService.sendSimpleEmail(email,
                "Congratulations, you passed your exam.\n" +
                        "Now you can get your certification\n" +
                        "\n" +
                        "\n" +
                        "thank you",
                "Certification"
        );

        return demandeAchatService.getDemandeAchatById(id);
    }

    @DeleteMapping("/deleteDemandeAchat/{id}")
    private void deleteDemandeAchat(@PathVariable("id") int id)
    {
        iDemandeAchatService.deleteDemandeAchat(id);
    }

    @PutMapping("/updateDemandeAchat")
    private DemandeAchat updateDemandeAchat(@RequestBody DemandeAchat d)
    {
        return iDemandeAchatService.updateDemandeAchat(d);

    }
    @PostMapping("AssignDemandeAchatToAppelOffre/{idDemande}/{idAppel}")

    public void AssignDemandeAchatToAppelOffre(@PathVariable("idDemande") int idDemande, @PathVariable("idAppel") int idAppel ) {
        iDemandeAchatService.AssignDemandeAchatToAppelOffre(idDemande,idAppel);
    }
    @PostMapping("etatDemandeAchat/{idDemande}/{idAppel}")
    public String etatDemandeAchat(@PathVariable("idDemande") int idDemande , @PathVariable("idAppel") int idAppel , Principal principal){
        return iDemandeAchatService.etatDemandeAchat(idDemande,idAppel , principal);
    }
    @PostMapping("/budget/{idDemande}")
    public float BudgetDamandeAchat(@PathVariable("idDemande") int idDemande){
        return iDemandeAchatService.BudgetDamandeAchat(idDemande);

    }
    @GetMapping("/getDemandeAchatByUser")
    public List<DemandeAchat> getDemandeAchatByUser(){
        return iDemandeAchatService.getDemandeAchatByUser();
    }

    @PostMapping("CreateDemandeAchat")
    public DemandeAchat createDemandeAchat(@RequestBody DemandeAchat demandeAchat) {
        return iDemandeAchatService.createDemandeAchat(demandeAchat ) ;}

    //////////////////////////////////////////////////////////////////////
    @GetMapping ("/AllNatureArticle")
    @ResponseBody
    public List<NatureArticle> getAllNatureArticle(){
        return iNatureArticleService.getAllNatureArticle();
    }

    @PostMapping("/addNatureArticle")
    @ResponseBody
    public NatureArticle addNatureArticle (@RequestBody NatureArticle n){
        return iNatureArticleService.addNatureArticle(n);
    }

    @GetMapping("/getNatureArticle/{id}")
    @ResponseBody
    public NatureArticle  getNatureArticleById(@PathVariable("id") int id){
        return   iNatureArticleService.getNatureArticleById(id);
    }


    @DeleteMapping("/deleteNatureArticle/{id}")
    private void deleteNatureArticle(@PathVariable("id") int id)
    {
        iNatureArticleService.deleteNatureArticle(id);
    }

    @PutMapping("/updateNatureArticle")
    private NatureArticle updateNatureArticle(@RequestBody NatureArticle n)
    {
        return iNatureArticleService.updateNatureArticle(n);

    }
    @PostMapping("/addAndAssignNatureArticleToArticle/{idArticle}")
    @ResponseBody
    NatureArticle addAndAssignNatureArticleToArticle(@RequestBody NatureArticle nature,@PathVariable("idArticle") int idArticle ){

        return iNatureArticleService.addAndAssignNatureArticleToArticle(nature, idArticle);
    }


    //////////////////////////////////////////////////////////////////
    @GetMapping ("/getAllArticleByIdDemandeAchat/{idDemande}")
    @ResponseBody
    public List<Article> getAllArticleByIdDemandeAchat(@PathVariable("idDemande") int idDemande){
        return iArticleService.getAllArticleByIdDemandeAchat(idDemande) ;
    }
    @GetMapping ("/AllArticle")
    @ResponseBody
    public List<Article> getAllArticle(){

        return iArticleService.getAllArticle();
    }

    @PostMapping("/addArticle")
    @ResponseBody
    public Article addArticle (@RequestBody Article a){


        return iArticleService.addArticle(a);
    }

    @GetMapping("/getArticle/{id}")
    @ResponseBody
    public Article  getArticleById(@PathVariable("id") int id){
        return   iArticleService.getArticleById(id);
    }
    @PostMapping("/addArticleAndAssignToUniteAndNature/{idUnite}/{idNature}")
    @ResponseBody
    public Article addArticleAndAssignToUniteAndNature(@RequestBody Article article,@PathVariable("idUnite") int idUnite,@PathVariable("idNature") int idNature){
        return iArticleService.addArticleAndAssignToUniteAndNature(article,idUnite,idNature);

    }

    @DeleteMapping("/deleteArticle/{id}")
    private void deleteArticle(@PathVariable("id") int id)
    {
        iArticleService.deleteArticle(id);
    }

    @PutMapping("/updateArticle")
    private Article updateArticle(@RequestBody Article a)
    {
        return iArticleService.updateArticle(a);

    }
    @PostMapping("/AddassignArticleToDemandeAchat/{idDemande}")
    public Article AddassignArticleToDemandeAchat(@RequestBody Article article, @PathVariable("idDemande") int idDemande){
        return iArticleService.AddassignArticleToDemandeAchat(article,idDemande);
    }
    @PostMapping("/assignArticleToDemandeAchat/{idArticle}/{idDemande}")
    public void assignArticleToDemandeAchat(@PathVariable("idArticle") int idArticle, @PathVariable("idDemande")int idDemande) {
        iArticleService.assignArticleToDemandeAchat(idArticle,idDemande);
    }

    @GetMapping("/SearchMultiple/{keyword}")
    @ResponseBody
    public List<Article> SearchMultiple(@PathVariable("keyword") String key)
    {
        return iArticleService.SearchMultiple(key);
    }


    //////////////////////////////////////////////////////////////////
    @GetMapping ("/AllUnite")
    @ResponseBody
    public List<UniteArticle> getAllUnite(){
        return iUniteService.getAllUnite();
    }

    @PostMapping("/addUnite")
    @ResponseBody
    public UniteArticle addUnite (@RequestBody UniteArticle a){
        return iUniteService.addUnite(a);
    }

    @GetMapping("/getUnite/{id}")
    @ResponseBody
    public UniteArticle  getUniteById(@PathVariable("id") int id){
        return   iUniteService.getUniteById(id);
    }


    @DeleteMapping("/deleteUnite/{id}")
    private void deletUnite(@PathVariable("id") int id)
    {
        iUniteService.deleteUnite(id);
    }

    @PutMapping("/updateUnite")
    private UniteArticle updateUnite(@RequestBody UniteArticle a)
    {
        return iUniteService.updateUnite(a);

    }
    @PostMapping("/addAndAssignUniteToArticle/{idArticle}")
    @ResponseBody
    UniteArticle addAndAssignUniteToArticle(@RequestBody UniteArticle unite,@PathVariable("idArticle") int idArticle ){

        return iUniteService.addAndAssignUniteToArticle(unite, idArticle);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////



    @PostMapping("/addComment/{idArticle}")
    @ResponseBody
    public Comment AddAffectCommentList(@RequestBody Comment comment, @PathVariable("idArticle") int idArticle
            ,Principal principal ) throws IOException {


        return    iCommentService.AddAffectCommentList(comment, idArticle ,principal);


    }
    @GetMapping("/getComment/{id}")
    @ResponseBody
    public Comment  getCommentById(@PathVariable("id") int id){
        return   iCommentService.getCommentById(id);
    }

    @PutMapping("/updateComment")
    @ResponseBody
    public Comment updateComment(@RequestBody Comment comment
            ,Principal principal)
    {
        return iCommentService.updateComment(comment,principal);

    }

    @GetMapping("/getAllComment")
    @ResponseBody
    public List<Comment> getAllComments()
    { return iCommentService.getAllComments(); }


    @DeleteMapping("/deleteComment/{idComment}")
    public void deleteComment(@PathVariable("idComment") Integer idComment)
    {
        iCommentService.deleteComment(idComment);
    }


    @GetMapping("/getAllCommentsByArticle/{idArticle}")
    @ResponseBody
    public List<Comment> getAllCommentsByArticle(@PathVariable(name = "idArticle") int idArticle)
    {
        return  iCommentService.getAllCommentsByArticle(idArticle);
    }

    @PostMapping("/save/{idComment}")
    public ResponseEntity<ReactComment> save(@PathVariable("idComment") int idComment ,
                                             @RequestBody ReactComment reactComment ,
                                             Principal principal){

        return  iCommentService.save(idComment,reactComment , principal) ;
    }


    @GetMapping("/findAllByIdComment/{idComment}")
    public List<ReactComment> findAllByIdComment(@PathVariable("idComment") int idComment){
        return  iCommentService.findAllByIdComment(idComment);
    }

    @GetMapping("/emoji/{idComment}/{react}")

    public List<ReactComment> findAllByCommentIdAndEmoji(@PathVariable("idComment") int idComment, @PathVariable("react") React react){
        return  iCommentService.findAllByIdCommentAndEmoji(idComment,react);
    }

    @GetMapping("/count/{idComment}")
    public Integer countAllByIdComment(@PathVariable("idComment") int idComment){
        return iCommentService.countAllByIdComment(idComment) ;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> search(@RequestParam(required = false) String nomarticle,
                                                @RequestParam(required = false) String descriptionarticle,
                                                @RequestParam(required = false) Float prixestime,
                                                @RequestParam(required = false) Integer quantite) {
        List<Article> articles = iArticleService.findByCriteria(nomarticle, descriptionarticle, prixestime, quantite);
        return ResponseEntity.ok(articles);
    }

}
