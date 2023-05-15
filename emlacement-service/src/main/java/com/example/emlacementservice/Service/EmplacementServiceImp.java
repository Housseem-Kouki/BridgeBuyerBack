package com.example.emlacementservice.Service;



import com.example.emlacementservice.Entities.AdresseExpedition;
import com.example.emlacementservice.Entities.Departement;
import com.example.emlacementservice.Entities.Emplacement;
import com.example.emlacementservice.Repository.AdresseExpeditionRepository;
import com.example.emlacementservice.Repository.DepartementRepository;
import com.example.emlacementservice.Repository.EmplacementRepository;
import com.example.emlacementservice.Repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class EmplacementServiceImp implements IEmplacementService {
    EmplacementRepository emplacementRepository;
    DepartementRepository departementRepository;
    UserRepository userRepository;
    AdresseExpeditionRepository adresseExpeditionRepository;

    @Override
    public Emplacement addEmplacement(Emplacement e, int idA) {
        AdresseExpedition adEx=adresseExpeditionRepository.findById(idA).get();
        e.setAdresseExpedition(adEx);
        return emplacementRepository.save(e);
    }

    @Override
    public Emplacement updateEmplacement(Emplacement e , int id) {



        e.setIdEmplacement(id);

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
        return emplacementRepository.findAll();
    }

    @Override
    public void affecterEmplacementToDepartement(Integer idE, Integer idD) {
        Departement departement = departementRepository.findById(idD).get();
        Emplacement emplacement = emplacementRepository.findById(idE).get();
        emplacement.getDepartements().add(departement);
        emplacementRepository.save(emplacement);

    }

    @Override
    public List<Emplacement> findAllEmplacementTrié() {
        // TODO Auto-generated method stub
        return emplacementRepository.findAllByOrderByNomEmplacementAsc();
    }

    @Scheduled(cron ="* * */1 * * *")
    @Override
    public void supprimerEmplacementsNonAffectes() {
        List<Emplacement> emplacements = emplacementRepository.findAll();

        for (Emplacement emplacement : emplacements) {
            if (emplacement.getUsers() == null || emplacement.getUsers().isEmpty()) {
                emplacementRepository.delete(emplacement);
            }
        }
    }
}
