package com.example.student.repository;

import com.example.student.model.Menu;
import com.example.student.model.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByMenuDateOrderByMealType(LocalDate menuDate);

    Optional<Menu> findByMenuDateAndMealType(
            LocalDate menuDate,
            MealType mealType
    );
}