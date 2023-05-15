package com.example.demandeachatservice.Services.DemandeAchat.Comment;

import com.example.demandeachatservice.Entities.*;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.example.demandeachatservice.Repository.CommentRepository;
import com.example.demandeachatservice.Repository.ReactCommentRepository;
import com.example.demandeachatservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
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
    public Comment AddAffectCommentList(Comment comment, int idArticle , Principal principal) throws IOException {

        Article article =  articleRepository.findById(idArticle).orElse(null);
        User user =  userRepository.findById(55).orElse(null);
        comment.setContent(BadWord.filterBadWords(comment.getContent()));

        comment.setArticle(article);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment, Principal principal) {

        User user =  userRepository.findById(55).orElse(null);



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

    @Override
    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null );
    }

    @Override
    public ResponseEntity<ReactComment> save(int idComment, ReactComment reactComment ,Principal principal) {
        String test="" ;
        Comment comment = commentRepository.findById(idComment).orElse(null) ;

        User user =userRepository.findById(55).orElse(null) ; //session
        ReactComment existingReactComment = reactCommentRepository.findByUserAndComments(user,comment);
        if (existingReactComment != null && !reactComment.getReact().equals(existingReactComment.getReact())) {
            existingReactComment.setReact(reactComment.getReact());
            reactCommentRepository.save(existingReactComment);
            return ResponseEntity.ok(reactComment);

        } else if (existingReactComment != null && reactComment.getReact().equals(existingReactComment.getReact())){
            reactCommentRepository.deleteById(existingReactComment.getIdReact());
            return  ResponseEntity.ok().build();
        } else {
            reactComment.setUser(user);
            reactComment.setComments(comment);
            reactCommentRepository.save(reactComment);
            return ResponseEntity.ok(reactComment);

        }
    }


}
