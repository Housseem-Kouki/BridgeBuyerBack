package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.Comment;
import com.example.demandeachatservice.Entities.React;
import com.example.demandeachatservice.Entities.ReactComment;
import com.example.demandeachatservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
    List<ReactComment> findAllByComments_IdComment(@Param("idComment") int idComment) ;
    List<ReactComment> findAllByComments_IdCommentAndReact(@Param("idComment") int idComment , @Param("react") React react );
    //@Query("select r from ReactComment r , User u , Comment c where r.comments.idComment=?1 and c.user.idUser=?2 ")
    ReactComment findByUserAndComments(User user  , Comment comment);



}