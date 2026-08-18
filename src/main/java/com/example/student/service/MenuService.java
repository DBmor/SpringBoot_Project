package com.example.student.service;

import com.example.student.model.Menu;
import com.example.student.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getTodayMenu() {

        return menuRepository.findByMenuDateOrderByMealType(
                LocalDate.now()
        );
    }

    public List<Menu> getMenuByDate(LocalDate date) {

        return menuRepository.findByMenuDateOrderByMealType(date);
    }

    public Menu getMenuById(Long id) {

        return menuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu not found")
                );
    }
}