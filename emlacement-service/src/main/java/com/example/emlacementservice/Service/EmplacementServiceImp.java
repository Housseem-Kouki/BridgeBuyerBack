package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.Departement;
import com.example.emlacementservice.Entities.Emplacement;
import com.example.emlacementservice.Repository.DepartementRepository;
import com.example.emlacementservice.Repository.EmplacementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmplacementServiceImp implements IEmplacementService {

    EmplacementRepository emplacementRepository ;
    DepartementRepository departementRepository;


    @Override
    public Emplacement addEmplacement(Emplacement e) {
        return emplacementRepository.save(e);
    }

    @Override
    public Emplacement updateEmplacement(Emplacement e) {
        return emplacementRepository.save(e);
    }

    @Override
    public void deleteEmplacement(int id) {
        emplacementRepository.deleteById(id);

    }

    @Override
    public Emplacement getEmplacementById(int id) {
        return emplacementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Emplacement> getAllEmplacements() {
        return emplacementRepository.findAll() ;
    }

    @Override
    public void affecterEmplacementToDepartement(Integer idE, Integer idD) {
        Departement departement=departementRepository.findById(idD).get();
        Emplacement emplacement=emplacementRepository.findById(idE).get();
        emplacement.getDepartements().add(departement);
        emplacementRepository.save(emplacement);

    }
   /* @Scheduled(cron = "0 0 0 * * *")
    public void cleanupEmplacements() {
        List<Emplacement> emplacementsToDelete = emplacementRepository.getEmplacementsOlderThanOneYear();
        emplacementRepository.deleteAll(emplacementsToDelete);
    }
    // Schedule la vérification des emplacements sans utilisateur assigné chaque jour à minuit
   /* @Scheduled(cron = "0 0 0 * * ?")
    public void verifierEmplacementsSansUtilisateurAssigné() {
        List<Emplacement> emplacements = emplacementRepository.findByUserIsNull();
        for (Emplacement emplacement : emplacements) {
            // Si l'emplacement est inactif, on le désactive
            if (!emplacement.isActive()) {
                continue;
            }
            // On assigne un utilisateur à l'emplacement
            User user = userService.getUtilisateurDisponiblePourEmplacement(emplacement);
            if (utilisateur != null) {
                emplacement.setUtilisateur(utilisateur);
                emplacementRepository.save(emplacement);
            } else {
                // Si aucun utilisateur disponible n'est trouvé, on désactive l'emplacement
                emplacement.setActive(false);
                emplacementRepository.save(emplacement);
            }
        }
    }
*/
}
