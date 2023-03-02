package com.example.demandeachatservice.Repository;


import com.example.demandeachatservice.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}