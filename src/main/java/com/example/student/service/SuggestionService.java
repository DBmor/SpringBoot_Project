package com.example.student.service;

import com.example.student.model.Suggestion;
import com.example.student.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuggestionService {
    
    private final SuggestionRepository suggestionRepository;
    
    public Suggestion createSuggestion(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }
    
    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }
    
    public List<Suggestion> getUnresolvedSuggestions() {
        return suggestionRepository.findByIsResolved(false);
    }
    
    public List<Suggestion> getSuggestionsByCategory(String category) {
        return suggestionRepository.findByCategory(category);
    }
    
    public Optional<Suggestion> getSuggestionById(Long id) {
        return suggestionRepository.findById(id);
    }
    
    public Suggestion updateSuggestion(Long id, Suggestion updatedSuggestion) {
        return suggestionRepository.findById(id).map(suggestion -> {
            suggestion.setSuggestion(updatedSuggestion.getSuggestion());
            suggestion.setDescription(updatedSuggestion.getDescription());
            suggestion.setCategory(updatedSuggestion.getCategory());
            suggestion.setIsResolved(updatedSuggestion.getIsResolved());
            return suggestionRepository.save(suggestion);
        }).orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }
    
    public void deleteSuggestion(Long id) {
        suggestionRepository.deleteById(id);
    }
}
