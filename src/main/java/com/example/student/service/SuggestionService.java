package com.example.student.service;

import com.example.student.model.Suggestion;
import com.example.student.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public SuggestionService(
            SuggestionRepository suggestionRepository
    ) {
        this.suggestionRepository = suggestionRepository;
    }

    public void saveSuggestion(
            String studentName,
            String rollNo,
            String suggestionText
    ) {

        Suggestion suggestion = new Suggestion();

        suggestion.setStudentName(studentName);
        suggestion.setRollNo(rollNo);
        suggestion.setDate(LocalDate.now());
        suggestion.setSuggestion(suggestionText);

        suggestionRepository.save(suggestion);
    }
}