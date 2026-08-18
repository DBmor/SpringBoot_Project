package com.example.student.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "suggestions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String studentName;
    
    @Column(nullable = false)
    private String studentEmail;
    
    @Column(nullable = false, length = 500)
    private String suggestion;
    
    @Column(length = 1000)
    private String description;
    
    @Column(nullable = false)
    private String category; // e.g., "New Item", "Improvement", "General"
    
    private Boolean isResolved;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        isResolved = false;
    }
}
