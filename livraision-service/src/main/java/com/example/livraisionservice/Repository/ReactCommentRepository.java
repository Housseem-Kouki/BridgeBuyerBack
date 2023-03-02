package com.example.livraisionservice.Repository;

import com.example.livraisionservice.Entities.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
}