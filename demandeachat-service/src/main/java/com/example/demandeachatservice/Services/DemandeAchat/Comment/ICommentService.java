package com.example.demandeachatservice.Services.DemandeAchat.Comment;

import com.example.demandeachatservice.Entities.Comment;
import com.example.demandeachatservice.Entities.React;
import com.example.demandeachatservice.Entities.ReactComment;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public interface ICommentService {
    void AddAffectCommentList(Comment comment, int idArticle) throws IOException;

    Comment updateComment (Comment comment,  int idArticle);

    List<Comment> getAllComments();

    void deleteComment(int idComment);

    List<Comment> getAllCommentsByArticle(int idArticle);
    public Response save(int idComment, ReactComment reactComment ) ;
    List<ReactComment> findAllByIdComment(int idComment);

    List<ReactComment> findAllByIdCommentAndEmoji(int idComment, React react);
    Integer  countAllByIdComment(int idComment) ;


}
