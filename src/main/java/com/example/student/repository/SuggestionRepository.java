package com.example.student.repository;

import com.example.student.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository
        extends JpaRepository<Suggestion, Long> {
}