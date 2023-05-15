package com.example.demandeachatservice.Services.DemandeAchat.Comment;

import com.example.demandeachatservice.Entities.Comment;
import com.example.demandeachatservice.Entities.React;
import com.example.demandeachatservice.Entities.ReactComment;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ICommentService {
    Comment AddAffectCommentList(Comment comment, int idArticle , Principal principal) throws IOException;

    Comment updateComment (Comment comment , Principal principal);

    List<Comment> getAllComments();

    void deleteComment(int idComment);

    List<Comment> getAllCommentsByArticle(int idArticle);
    public ResponseEntity<ReactComment> save(int idComment, ReactComment reactComment ,Principal principal ) ;
    List<ReactComment> findAllByIdComment(int idComment);

    List<ReactComment> findAllByIdCommentAndEmoji(int idComment, React react);
    Integer  countAllByIdComment(int idComment) ;
    public Comment getCommentById(int id);


}
