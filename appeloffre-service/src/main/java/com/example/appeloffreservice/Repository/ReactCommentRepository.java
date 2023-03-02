package com.example.appeloffreservice.Repository;

import com.example.appeloffreservice.Entities.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
}