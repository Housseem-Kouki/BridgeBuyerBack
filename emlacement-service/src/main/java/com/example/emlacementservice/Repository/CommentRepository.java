package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}