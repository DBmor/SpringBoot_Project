package com.example.student.repository;

import com.example.student.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findByIsResolved(Boolean isResolved);
    List<Suggestion> findByCategory(String category);
}
