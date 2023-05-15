package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.Article;
import com.example.appeloffreservice.Entities.DemandeAchat;
import com.example.appeloffreservice.Repository.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppeloffreServiceImp implements IAppeloffreService{
    AppelOffreRepository appelOffreRepository;
    DemandeAchatRepository demandeAchatRepository;
    OffreRepository offreRepository;
    ArticleRepository articleRepository;
    private final PaimentRepository paimentRepository;

    @Override
    public AppelOffre addAndAssignAppelToDemande(AppelOffre appelOffre ,int iddemandeachat ) {
        DemandeAchat demandeAchat=demandeAchatRepository.findById(iddemandeachat).orElse(null);
        appelOffre.setPrixInitiale(demandeAchat.getBudget());


        appelOffre.setDemandeAchat(demandeAchat);
        appelOffre.getDemandeAchat().getArticles();
        appelOffre.setEtat(0); // en cours

        return appelOffreRepository.save(appelOffre);
    }

    @Override
    public AppelOffre updateAppelOffre(AppelOffre appelOffre) {
        return appelOffreRepository.save(appelOffre);
    }

    @Override
    public void deleteAppelOffre(int id) {
        appelOffreRepository.deleteById(id);

    }

    @Override
    public AppelOffre getAppelOffrebyId(int id) {
        return appelOffreRepository.findById(id).orElse(null);

    }

    @Override
    public List<AppelOffre> getAllAppelOffre() {
        return appelOffreRepository.findAll();
    }

    @Override
    public List<AppelOffre> getAppelOffreByEtat(int etat) {
        List<AppelOffre> ap=appelOffreRepository.findAppelOffreByEtat(etat);
        AppelOffre A1=ap.get(0);
        if(A1.getEtat()==1){
            return ap;
        }else if (A1.getEtat()==2){
            return ap;
        }else {
            return ap;
        }

    }


    @Override
    public List<AppelOffre> retrieveAppelOffer(int etat) {
        return appelOffreRepository.findAppelOffreByEtat(etat);
    }

    @Override
    public List<AppelOffre> Search(String key) {
        if (key.equals("")){
            return (List<AppelOffre>) appelOffreRepository.findAll();
        }else
        {
            return appelOffreRepository.rech(key);
        }

    }
    @Override
    public List<AppelOffre> findByCriteria(String reference, String commentaire, double prixInitiale) {
        return appelOffreRepository.findAll()
                .stream()
                .filter(appelOffre -> reference == null || appelOffre.getReference().contains(reference))
                .filter(appelOffre -> commentaire == null || appelOffre.getCommentaire().contains(commentaire))
                .filter(appelOffre -> prixInitiale == 0 || appelOffre.getPrixInitiale()==prixInitiale)
                .collect(Collectors.toList());
    }


 /*   @Override
    public String getEtatt(int etat) {


    List<AppelOffre> la=appelOffreRepository.findAppelOffreByEtat(etat);
        for (AppelOffre a :la) {
            if (a.getEtat()== 0) {
                System.out.println("offre expire =" + etat);
            }
            if (a.getEtat() == 1) {
                System.out.println("offre en cours =" + etat);
            }
            if (a.getEtat() == 2) {
                System.out.println("offre fermee =" + etat);
            }
        }

        String res="l'appel d'offre est expiree\n"+
                    "l'appel d'offre est en cours\n"+
                    "l'appel d'offre est fermee\n";
        return res;

    }*/


    @Override
    public void export(HttpServletResponse response , int idAppel) throws IOException {
        //  List<AppelOffre> appelOffre=appelOffreRepository.findAll();
        AppelOffre appelOffre=appelOffreRepository.findById(idAppel).orElse(null);
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle= FontFactory.getFont(FontFactory.COURIER_BOLD,Font.ITALIC);
        fontTitle.setSize(25);
        fontTitle.setColor(Color.PINK);

        Paragraph paragraph =new Paragraph("Votre Appel d'offre \n \n", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.COURIER);
        fontParagraph.setSize(18);
        fontParagraph.setColor(Color.BLACK);


        Paragraph p1=new Paragraph("Commentaire du client:  "+appelOffre.getCommentaire(), fontParagraph);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2=new Paragraph("Prix initiale proposé par le client:  "+appelOffre.getPrixInitiale(), fontParagraph);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p3=new Paragraph("Prix initiale proposé par le client:  "+appelOffre.getReference(), fontParagraph);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p4=new Paragraph("Date de creation de l'appel d'offre:  "+appelOffre.getDateCreationC(), fontParagraph);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);


        document.close();
        //AppelOffre(appelOffre.getCommentaire(),fontParagraph);
    }
}


