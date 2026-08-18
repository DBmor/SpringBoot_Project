package com.example.student.controller;

import com.example.student.model.Suggestion;
import com.example.student.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SuggestionController {
    
    private final SuggestionService suggestionService;
    
    @GetMapping
    public ResponseEntity<List<Suggestion>> getAllSuggestions() {
        return ResponseEntity.ok(suggestionService.getAllSuggestions());
    }
    
    @GetMapping("/unresolved")
    public ResponseEntity<List<Suggestion>> getUnresolvedSuggestions() {
        return ResponseEntity.ok(suggestionService.getUnresolvedSuggestions());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Suggestion> getSuggestionById(@PathVariable Long id) {
        return suggestionService.getSuggestionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Suggestion> createSuggestion(@RequestBody Suggestion suggestion) {
        return ResponseEntity.ok(suggestionService.createSuggestion(suggestion));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Suggestion> updateSuggestion(@PathVariable Long id, @RequestBody Suggestion suggestion) {
        return ResponseEntity.ok(suggestionService.updateSuggestion(id, suggestion));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuggestion(@PathVariable Long id) {
        suggestionService.deleteSuggestion(id);
        return ResponseEntity.noContent().build();
    }
}
