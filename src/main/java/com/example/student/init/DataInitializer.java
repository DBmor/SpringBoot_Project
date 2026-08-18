package com.example.student.init;

import com.example.student.model.MealType;
import com.example.student.model.Menu;
import com.example.student.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MenuRepository menuRepository;

    public DataInitializer(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(String... args) {

        LocalDate today = LocalDate.now();

        if (menuRepository
                .findByMenuDateAndMealType(
                        today,
                        MealType.BREAKFAST
                )
                .isEmpty()) {

            menuRepository.save(
                    new Menu(
                            today,
                            MealType.BREAKFAST,
                            "Thepla, Dahi, Chutney, Tea"
                    )
            );
        }

        if (menuRepository
                .findByMenuDateAndMealType(
                        today,
                        MealType.LUNCH
                )
                .isEmpty()) {

            menuRepository.save(
                    new Menu(
                            today,
                            MealType.LUNCH,
                            "Roti, Gujarati Dal, Rice, Mix Vegetable, Salad, Chaas"
                    )
            );
        }

        if (menuRepository
                .findByMenuDateAndMealType(
                        today,
                        MealType.DINNER
                )
                .isEmpty()) {

            menuRepository.save(
                    new Menu(
                            today,
                            MealType.DINNER,
                            "Khichdi, Kadhi, Bhakhri, Sabzi, Salad"
                    )
            );
        }

        System.out.println("Today's menu loaded successfully!");
    }
}