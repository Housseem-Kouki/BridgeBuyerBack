package com.example.demandeachatservice.Services.DemandeAchat.Comment;

import com.example.demandeachatservice.Entities.Comment;
import com.example.demandeachatservice.Entities.React;
import com.example.demandeachatservice.Entities.ReactComment;

import java.util.List;

public interface ICommentService {
    void AddAffectCommentList(Comment comment, int idArticle, int idUser);

    Comment updateComment (Comment comment,  int idArticle, int idUser);

    List<Comment> getAllComments();

    void deleteComment(int idComment);

    List<Comment> getAllCommentsByArticle(int idArticle);
    public ReactComment save(int idComment, ReactComment reactComment ) ;
    List<ReactComment> findAllByIdComment(int idComment);

    List<ReactComment> findAllByIdCommentAndEmoji(int idComment, React react);
    Integer  countAllByIdComment(int idComment) ;

}
