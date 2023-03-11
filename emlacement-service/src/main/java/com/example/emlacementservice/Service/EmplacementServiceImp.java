package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.*;
import com.example.emlacementservice.Repository.DepartementRepository;
import com.example.emlacementservice.Repository.DeviseRepository;
import com.example.emlacementservice.Repository.EmplacementRepository;
import com.example.emlacementservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmplacementServiceImp implements IEmplacementService {

    EmplacementRepository emplacementRepository ;
    DepartementRepository departementRepository;
    UserRepository userRepository;
    private final DeviseRepository deviseRepository;


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
    public Emplacement affecterEmplacementToDepartement(Emplacement e, Integer idD) {
        Departement departement=departementRepository.findById(idD).orElse(null);
        departement.setDepartementEmplacement(e);
      return   emplacementRepository.save(e);

    }

    @Override
    public Page<Emplacement> findAllEmplacementTrié(Pageable pageable) {
        // TODO Auto-generated method stub
        return emplacementRepository.findAllByOrderByNomEmplacementAsc(pageable);
    }


    @Scheduled(cron="* * */1 * * *")
    @Override
    public void supprimerEmplacementsNonAffectes() {
        List<Emplacement> emplacements = emplacementRepository.findAll();

        for (Emplacement emplacement : emplacements) {
            if (emplacement.getUsers() == null || emplacement.getUsers().isEmpty()) {
                emplacementRepository.delete(emplacement);
            }
        }
    }

    @Override
    @Transactional
    public Emplacement affectResponsableToUser(int idUserResponsble , int idEmplacement) {
        Emplacement emplacement = emplacementRepository.findById(idEmplacement).orElse(null);
        User user = userRepository.findById(idUserResponsble).orElse(null);
        user.setEmplacement(emplacement);
        emplacement.getUsers().add(user);

        userRepository.save(user);
        return emplacementRepository.save(emplacement);
    }
    /*@Override
    public List<Emplacement> filterByDeviseAndResponsable(Devise dev, User resp) {
        // TODO Auto-generated method stub
        return emplacementRepository.findByDeviseAndResponsableEmplacement(dev,resp);
    } */
    
  @Override
    public List<Emplacement> findByCriteria(String nomemp,String devise,String nomresp) {


      List<Emplacement> emps= emplacementRepository.findAll().stream()
              .filter( Emplacement -> nomemp == null ||Emplacement.getNomEmplacement().contains(nomemp))
              .filter( Emplacement -> devise == null || Emplacement.getDevise().getNomDevise().contains(devise))
              .filter( Emplacement -> nomresp == null || Emplacement.getResponsableEmplacement().getLname().contains(nomresp))
              .collect(Collectors.toList());
      return emps ;
  }

   /* @Override
    public List<Emplacement> searchEmplacement(String key) {
     return emplacementRepository.search(key);
 } */
}


