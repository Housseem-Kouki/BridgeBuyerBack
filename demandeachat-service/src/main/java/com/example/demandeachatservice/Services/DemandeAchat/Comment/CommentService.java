package com.example.demandeachatservice.Services.DemandeAchat.Comment;

import com.example.demandeachatservice.Entities.*;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.example.demandeachatservice.Repository.CommentRepository;
import com.example.demandeachatservice.Repository.ReactCommentRepository;
import com.example.demandeachatservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService implements ICommentService{
    ArticleRepository articleRepository ;
    UserRepository userRepository ;
    CommentRepository commentRepository;
    ReactCommentRepository reactCommentRepository ;

    @Override
    public void AddAffectCommentList(Comment comment, int idArticle, int idUser) {
        Article article =  articleRepository.findById(idArticle).orElse(null);
        User user =  userRepository.findById(idUser).orElse(null);


        comment.setArticle(article);
        comment.setUser(user);

        commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment, int idArticle, int idUser) {
        Article article =  articleRepository.findById(idArticle).orElse(null);
        User user =  userRepository.findById(idUser).orElse(null);


        comment.setArticle(article);
        comment.setUser(user);

       return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(int idComment) {
        log.info("In methode deleteComment");
        log.warn("Are you sure you want to delete Comment");

        commentRepository.deleteById(idComment);
        log.error("exeption");

    }

    @Override
    public List<Comment> getAllCommentsByArticle(int idArticle) {
        Article article =  articleRepository.findById(idArticle).orElse(null);
        return articleRepository.getAllCommentsByArticle(article);


    }

    @Override
    public ReactComment save(int idComment, ReactComment reactComment ) {
        Comment comment = commentRepository.findById(idComment).orElse(null) ;

        reactComment.setComments(comment);
        return  reactCommentRepository.save(reactComment);
    }

    @Override
    public List<ReactComment> findAllByIdComment(int idComment) {
        return  reactCommentRepository.findAllByComments_IdComment(idComment);
    }

    @Override
    public List<ReactComment> findAllByIdCommentAndEmoji(int idComment, React react) {
        return reactCommentRepository.findAllByComments_IdCommentAndReact(idComment,react);    }

    @Override
    public Integer countAllByIdComment(int idComment) {
        int nb=0 ;

        List<ReactComment> lr = reactCommentRepository.findAllByComments_IdComment(idComment);
        for(ReactComment r : lr) {
            nb++ ;

        }

        return  nb ;
    }

}
