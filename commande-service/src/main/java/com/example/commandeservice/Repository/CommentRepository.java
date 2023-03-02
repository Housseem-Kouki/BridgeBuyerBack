package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}