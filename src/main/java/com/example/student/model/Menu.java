package com.example.student.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate menuDate;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Column(length = 1000)
    private String mealItems;

    public Menu() {
    }

    public Menu(LocalDate menuDate, MealType mealType, String mealItems) {
        this.menuDate = menuDate;
        this.mealType = mealType;
        this.mealItems = mealItems;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate menuDate) {
        this.menuDate = menuDate;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public String getMealItems() {
        return mealItems;
    }

    public void setMealItems(String mealItems) {
        this.mealItems = mealItems;
    }
}