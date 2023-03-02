package com.example.appeloffreservice.Repository;

import com.example.appeloffreservice.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}