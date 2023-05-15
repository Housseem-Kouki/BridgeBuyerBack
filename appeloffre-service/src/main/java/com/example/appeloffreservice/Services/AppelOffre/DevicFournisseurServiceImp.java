package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.Article;
import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.User;
import com.example.appeloffreservice.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class DevicFournisseurServiceImp implements IDevisFournisseurService
{
    UserRepository userRepository;
    DevisFourniseurRepository devisFourniseurRepository;
    AppelOffreRepository appelOffreRepository;
//     UserRepository userRepository;

    private final DemandeAchatRepository demandeAchatRepository;
    private final DepartementRepository departementRepository;
    private final DeviseRepository deviseRepository;
    private final ArticleRepository articleRepository;

    @Override
    public DevisFourniseur addDevisFourniseur(DevisFourniseur devisFourniseur) {
        // Set<Article> ls=  devisFourniseur.getAppelOffre().getDemandeAchat().getArticles();
        return devisFourniseurRepository.save(devisFourniseur);
    }

    @Override
    public DevisFourniseur updateDevisFourniseur(DevisFourniseur devisFourniseur) {
        return devisFourniseurRepository.save(devisFourniseur);
    }

    @Override
    public void deleteDevisFourniseur(int id) {
        devisFourniseurRepository.deleteById(id);
    }

    @Override
    public DevisFourniseur getDevisFourniseurbyId(int id) {
        return devisFourniseurRepository.findById(id).orElse(null) ;
    }


    @Override
    public List<DevisFourniseur> getAllDevisFourniseur() {
        return devisFourniseurRepository.findAll();
    }



    //ykhtar bel min prix w delais + disponible + etat expire
    @Override
    public DevisFourniseur comparerDevis(int id1 , int id2) {
        List<DevisFourniseur> df=devisFourniseurRepository.findByIds(id1,id2);
        DevisFourniseur df1=df.get(0);
        DevisFourniseur df2=df.get(1);
        if ((df1.getPtotal()<df2.getPtotal()) && (df1.getDelaiLivraison()<df2.getDelaiLivraison()) && (df1.isDisopnible() ) && (df1.getEtat()==1)){
            return df1;
        } if ((df1.getPtotal()<df2.getPtotal())  && (df1.isDisopnible() && (df1.getEtat()==1))){
            return df1;
        }
        if ((df1.getDelaiLivraison()<df2.getDelaiLivraison()&& (df1.isDisopnible() ) && (df1.getEtat()==1))){
            return df2;
        }
        if ((df1.getPtotal()<df2.getPtotal()) && (df1.isDisopnible() ) && (df1.getEtat()==1)){
            return df2;
        }
        else {
            return null;
        }
    }

    @Override
    public DevisFourniseur comparerListDevis(DevisFourniseur devisFourniseur) {

        /*DevisFourniseur devisFourniseur=devisFourniseurRepository.findAll();
        for (DevisFourniseur devisFourniseur1 : devisFourniseur)
        { if (devisFourniseur1.getPtotal()<devisFourniseur1.getPtotal() && devisFourniseur1.isDisopnible()){

        }

        }*/
        return null;
    }

    @Override
    public List<DevisFourniseur> getDevisfournisseurByIDAppel(int idAppelOffre) {

        return devisFourniseurRepository.findDevisFourniseurByIdAppeloffre(idAppelOffre);
    }

    @Override
    public float calculremise(int id1 ,int idA, float pourcentageRemise) {
        float Ptotal = 0;
        DevisFourniseur devisFourniseur=devisFourniseurRepository.findById(id1).orElse(null);
        for (Article article : devisFourniseur.getAppelOffre().getDemandeAchat().getArticles() ){
            if(article.getIdarticle() == idA){
                Ptotal =   article.getPrixestime() - (pourcentageRemise * article.getPrixestime() / 100);
                article.setPrixestimeAvecRemise(Ptotal);
                articleRepository.save(article);
            }
        }
        devisFourniseurRepository.save(devisFourniseur);
        return Ptotal;


    }


    //  devisFourniseur.getAppelOffre().getDemandeAchat().getArticles();
    // Article article=articleRepository.findById(idA).orElse(null);
    // DevisFourniseur devis1=devis.get(0);
    //  List<Article> articles = new ArrayList<Article>();
    //   for(Article a:articles) {
    //       double Ptotal = article.getPrixestime() - (pourcentageRemise * article.getPrixestime() / 100);

    // devisFourniseur.setPtotal(Ptotal);


    @Override
    public List<DevisFourniseur> getDevisFournisseur(int idFournisseur) {
       /* User user=userRepository.findById(idFournisseur).orElse(null);
        List <DevisFourniseur> devisFourniseurs=devisFourniseurRepository.findById(idFournisseur).orElse(null);
        devisFourniseurs.getFourniseur();*/

        return devisFourniseurRepository.findDevisFourniseursByFourniseur(userRepository.findById(idFournisseur).orElse(null)) ;
    }

    @Override
    public DevisFourniseur updateTotalDevis(int idDevis) {
        double totalDevis = 0;
        DevisFourniseur devisFourniseur= devisFourniseurRepository.findById(idDevis).orElse(null);
        for (Article article : devisFourniseur.getAppelOffre().getDemandeAchat().getArticles()){
            totalDevis+= article.getPrixestimeAvecRemise();
        }
        devisFourniseur.setPtotal(totalDevis);
        return devisFourniseurRepository.save(devisFourniseur);
    }



    @Override
    public void assignAppeloffreAndUserToDevis( int idAppeloffre ,int idDevis, int idUser) {
        AppelOffre appelOffre =appelOffreRepository.findById(idAppeloffre).orElse(null);
        DevisFourniseur devisFourniseur= devisFourniseurRepository.findById(idDevis).orElse(null);
        User user=userRepository.findById(idUser).orElse(null);
        devisFourniseur.setFourniseur(user);
        devisFourniseur.setAppelOffre(appelOffre);
        devisFourniseur.setPrixInitiale(appelOffre.getPrixInitiale(

        ));
        devisFourniseurRepository.save(devisFourniseur);
    }


    @Override
    public DevisFourniseur addAndassignAppelAndUserToDevis(DevisFourniseur devisFourniseur,
                                                           int idAppeloffre, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        AppelOffre appelOffre =appelOffreRepository.findById(idAppeloffre).orElse(null);
        System.out.println(appelOffre.getPrixInitiale()+"cccccccccccccc");


        devisFourniseur.setFourniseur(user);
        devisFourniseur.setAppelOffre(appelOffre);
        devisFourniseur.setPrixInitiale(appelOffre.getPrixInitiale());
        return devisFourniseurRepository.save(devisFourniseur);
    }

    @Override
    public DevisFourniseur addDevisAndassignAppelToDevis(DevisFourniseur devisFourniseur, int idAppeloffre , int idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        devisFourniseur.setFourniseur(user);
        AppelOffre appelOffre =appelOffreRepository.findById(idAppeloffre).orElse(null);
        devisFourniseur.setAppelOffre(appelOffre);

        devisFourniseur.setPrixInitiale(appelOffre.getPrixInitiale());
        devisFourniseur.setEtat(0); // devis en cours
        return devisFourniseurRepository.save(devisFourniseur);
    }

    //@Override
    //@Scheduled(cron = "0 0 12 28 1/1  *")
    /* @Scheduled(cron = "*///60 * * * * *")
   /* public void deleteExpiredOffer() {
        Date d = new Date();
        List<DevisFourniseur> offers = devisFourniseurRepository.findAll();
        for(DevisFourniseur df : offers){
            if(df.getPeriodeValidite().after(d)){
                devisFourniseurRepository.delete(df);
                System.out.println("Devis expiré");
            }
        }
    }*/
}
