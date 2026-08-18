package com.example.student.service;

import com.example.student.model.Review;
import com.example.student.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final MenuItemService menuItemService;
    
    public List<Review> getReviewsByMenuItemId(Long menuItemId) {
        return reviewRepository.findByMenuItemId(menuItemId);
    }
    
    public Review createReview(Review review) {
        Review savedReview = reviewRepository.save(review);
        menuItemService.updateAverageRating(review.getMenuItem().getId());
        return savedReview;
    }
    
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        Long menuItemId = review.getMenuItem().getId();
        reviewRepository.deleteById(id);
        menuItemService.updateAverageRating(menuItemId);
    }
    
    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(updatedReview.getRating());
            review.setComment(updatedReview.getComment());
            Review saved = reviewRepository.save(review);
            menuItemService.updateAverageRating(review.getMenuItem().getId());
            return saved;
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }
}
