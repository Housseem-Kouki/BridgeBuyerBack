package com.example.commandeservice.Repository;

import com.example.commandeservice.Entities.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
}