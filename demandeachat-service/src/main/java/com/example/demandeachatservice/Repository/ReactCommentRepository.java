package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.React;
import com.example.demandeachatservice.Entities.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
    List<ReactComment> findAllByComments_IdComment(@Param("idComment") int idComment) ;
    List<ReactComment> findAllByComments_IdCommentAndReact(@Param("idComment") int idComment , @Param("react") React react );
}