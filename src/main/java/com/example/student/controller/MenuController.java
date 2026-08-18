package com.example.student.controller;

import com.example.student.model.Menu;
import com.example.student.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/api/menu/today")
    public List<Menu> getTodayMenu() {

        return menuService.getTodayMenu();
    }
}