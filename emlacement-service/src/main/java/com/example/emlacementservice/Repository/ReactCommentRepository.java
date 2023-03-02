package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
}