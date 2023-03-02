package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactCommentRepository extends JpaRepository<ReactComment, Integer> {
}