package com.example.demandeachatservice.Services.DemandeAchat;

import com.example.demandeachatservice.Entities.*;
import com.example.demandeachatservice.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor

public class DemandeAchatService implements IDemandeAchatService {
    DemandeAchatRepository demandeAchatRepository;
    ArticleRepository articleRepository;

     NatureArticleRepository natureArticleRepository;
     UniteArticleRepository uniteRepository;
     AppelOffreRepository appelOffreRepository;
    JavaMailSender mailSender;
    private final DepartementRepository departementRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional

    public DemandeAchat addDemandeAchat(DemandeAchat d, int idNature, int idUnite) {

        Article article = saveArticle(d, idNature, idUnite);
User user =userRepository.findById(1).orElse(null) ;
        if (article.getDemandeAchats() == null) {
            article.setDemandeAchats(new HashSet<>());
            d.setAcheteur(user);

        }
        return demandeAchatRepository.save(d);
    }



    private Article saveArticle(DemandeAchat d, int idNature, int idUnite) {
        Set<Article> articles = (Set<Article>) d.getArticles();
        NatureArticle natureArticle = natureArticleRepository.findById(idNature).orElse(null);
        UniteArticle uniteArticle = uniteRepository.findById(idUnite).orElse(null);
        for (Article article : articles) {
            article.setUniteArticle(uniteArticle);
            article.setNatureArticle(natureArticle);

            return articleRepository.save(article);
        }
        return null;
    }


    @Override
    public DemandeAchat updateDemandeAchat(DemandeAchat d) {
        return demandeAchatRepository.save(d);
    }

    @Override
    public void deleteDemandeAchat(int id) {
        demandeAchatRepository.deleteById(id);
    }

    @Override
    public DemandeAchat getDemandeAchatById(int id) {
        return demandeAchatRepository.findById(id).orElse(null);
    }

    @Override
    public List<DemandeAchat> getAllDemandeAchat() {
        return demandeAchatRepository.findAll();
    }

    @Override
    public String etatDemandeAchat( int idDemande ,  int idAppel) {

        DemandeAchat d= demandeAchatRepository.findById(idDemande).orElse(null);
        if(d.getEtatdemandeachat()==0){
            return "demande en cours de traitement" ;
        }
        else if (d.getEtatdemandeachat()==1){
            AssignDemandeAchatToAppelOffre(idDemande,idAppel);

            return "demande accepter " ;
        }
        else
            deleteDemandeAchat(idDemande);

        return "demande d'achat refusé";
    }

    @Override
    public void AssignDemandeAchatToAppelOffre(int idDemande, int idAppel) {
        DemandeAchat demandeAchat = demandeAchatRepository.findById(idDemande).orElse(null);

        AppelOffre appelOffre=appelOffreRepository.findById(idAppel).orElse(null);


        appelOffre.setDemandeAchat(demandeAchat);

        demandeAchatRepository.save(demandeAchat);
    }

    @Override
    public float BudgetDamandeAchat(int idDemande ) {
      float  budgetTotal = 0;

        DemandeAchat demandeAchat = demandeAchatRepository.findById(idDemande).orElse(null);
        for (Article a : demandeAchat.getArticles()) {
            budgetTotal += a.getPrixestime();
        }
        demandeAchat.setBudget(budgetTotal);
        return demandeAchat.getBudget();
    }

    @Override
    public List<DemandeAchat> getDemandeAchatByUser() {
        return demandeAchatRepository.findDemandeAchatsByAcheteur(1);
    }

    //-----------------------send email----------------------------
    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("katerkarouf26@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }


    //----------------send mail  with attachement-------------------------
    public void sendEmailWithAttachment(
            String body,
            String subject,
            String attachment,User user) throws MessagingException {
        String email= user.getEmail();

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("katerkarouf26@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem
                = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailSender.send(mimeMessage);
        System.out.println("Mail Send...");

    }
}
