package com.example.demandeachatservice.Services.DemandeAchat;

import com.example.demandeachatservice.Entities.DemandeAchat;

import java.security.Principal;
import java.util.List;

public interface IDemandeAchatService {
    public DemandeAchat addDemandeAchat(DemandeAchat d  , Principal principal);
    public DemandeAchat updateDemandeAchat(DemandeAchat d);
    public void deleteDemandeAchat(int id);
    public DemandeAchat getDemandeAchatById(int id);
    public List<DemandeAchat> getAllDemandeAchat();
    public String etatDemandeAchat(int idDemande ,  int idAppel ,Principal principal) ;
    public void AssignDemandeAchatToAppelOffre(int idDemande, int idAppel ) ;
    public float BudgetDamandeAchat( int idDemande ) ;
    public List<DemandeAchat> getDemandeAchatByUser() ;

}
