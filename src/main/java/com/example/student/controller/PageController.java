package com.example.student.controller;

import com.example.student.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class PageController {

    private final MenuService menuService;

    public PageController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "todayMenu",
                menuService.getTodayMenu()
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd MMMM yyyy");

        model.addAttribute(
                "todayDate",
                LocalDate.now().format(formatter)
        );

        return "index";
    }
}