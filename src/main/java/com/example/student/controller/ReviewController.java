package com.example.student.controller;

import com.example.student.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public String submitReview(

            @RequestParam Long menuId,

            @RequestParam String studentName,

            @RequestParam String rollNo,

            @RequestParam Integer rating,

            @RequestParam(required = false) String review,

            RedirectAttributes redirectAttributes
    ) {

        reviewService.saveReview(
                menuId,
                studentName,
                rollNo,
                rating,
                review
        );

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Your review has been submitted successfully!"
        );

        return "redirect:/";
    }
}