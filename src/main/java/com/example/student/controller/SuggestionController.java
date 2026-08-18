package com.example.student.controller;

import com.example.student.service.SuggestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(
            SuggestionService suggestionService
    ) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/suggestions")
    public String submitSuggestion(

            @RequestParam String studentName,

            @RequestParam String rollNo,

            @RequestParam String suggestion,

            RedirectAttributes redirectAttributes
    ) {

        suggestionService.saveSuggestion(
                studentName,
                rollNo,
                suggestion
        );

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Thank you! Your suggestion has been submitted."
        );

        return "redirect:/";
    }
}