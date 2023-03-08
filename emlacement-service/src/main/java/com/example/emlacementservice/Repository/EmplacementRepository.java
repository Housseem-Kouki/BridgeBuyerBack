package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.Devise;
import com.example.emlacementservice.Entities.Emplacement;
import com.example.emlacementservice.Entities.User;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
    Page<Emplacement> findAllByOrderByNomEmplacementAsc(Pageable pageable);

   // List<Emplacement> findByDeviseAndResponsableEmplacement(Devise dev, User resp);


 /*@Query("SELECT emp FROM Emplacement emp inner join Devise d on emp.devise.idDevise=d.idDevise inner join User u on emp.responsableEmplacement.idUser=u.idUser inner join AdresseExpedition a on emp.adresseExpedition.idAdresseExpedition=a.idAdresseExpedition WHERE (:nomEmplacement IS NULL OR emp.nomEmplacement LIKE %:key%) AND (d.nomDevise IS NULL OR d.nomDevise LIKE %:key%) AND (u.Fname or u.Lname IS NULL OR LIKE %:key% ) AND (a.cite OR a.pays OR a.codePostal IS NULL OR LIKE %:key% )")

    List<Emplacement> search(@Param("key") String key); */


}