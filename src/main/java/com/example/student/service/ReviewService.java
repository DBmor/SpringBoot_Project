package com.example.student.service;

import com.example.student.model.Menu;
import com.example.student.model.Review;
import com.example.student.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MenuService menuService;

    public ReviewService(
            ReviewRepository reviewRepository,
            MenuService menuService
    ) {
        this.reviewRepository = reviewRepository;
        this.menuService = menuService;
    }

    public void saveReview(
            Long menuId,
            String studentName,
            String rollNo,
            Integer rating,
            String reviewText
    ) {

        Menu menu = menuService.getMenuById(menuId);

        Review review = new Review();

        review.setMenu(menu);
        review.setStudentName(studentName);
        review.setRollNo(rollNo);
        review.setRating(rating);
        review.setReview(reviewText);
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);
    }

    public List<Review> getReviewsByMenuId(Long menuId) {

        return reviewRepository.findByMenuId(menuId);
    }
}