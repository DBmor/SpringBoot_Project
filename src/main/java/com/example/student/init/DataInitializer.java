package com.example.student.init;

import com.example.student.model.MenuItem;
import com.example.student.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final MenuItemRepository menuItemRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (menuItemRepository.count() == 0) {
            initializeMenuItems();
        }
    }
    
    private void initializeMenuItems() {
        MenuItem[] menuItems = {
            // Vegetarian
            MenuItem.builder()
                .name("Paneer Butter Masala")
                .description("Soft paneer cubes in a rich and creamy tomato-based sauce")
                .price(280.0)
                .category("Vegetarian")
                .build(),
            
            MenuItem.builder()
                .name("Dal Makhani")
                .description("Black lentils and kidney beans cooked overnight with butter and cream")
                .price(250.0)
                .category("Vegetarian")
                .build(),
            
            MenuItem.builder()
                .name("Aloo Gobi")
                .description("Potatoes and cauliflower stir-fried with aromatic spices")
                .price(180.0)
                .category("Vegetarian")
                .build(),
            
         
            
            // Vegan
            MenuItem.builder()
                .name("Chana Masala")
                .description("Chickpeas in a flavorful onion and tomato sauce")
                .price(200.0)
                .category("Vegan")
                .build(),
            
            MenuItem.builder()
                .name("Vegetable Biryani")
                .description("Fragrant rice cooked with mixed vegetables")
                .price(220.0)
                .category("Vegan")
                .build(),
            
            // Dessert
            MenuItem.builder()
                .name("Gulab Jamun")
                .description("Soft milk balls soaked in warm sugar syrup")
                .price(120.0)
                .category("Dessert")
                .build(),
            
            MenuItem.builder()
                .name("Kheer")
                .description("Rice pudding with condensed milk and dry fruits")
                .price(100.0)
                .category("Dessert")
                .build(),
            
            // Beverage
            MenuItem.builder()
                .name("Masala Chai")
                .description("Traditional Indian tea with aromatic spices")
                .price(50.0)
                .category("Beverage")
                .build(),
            
            MenuItem.builder()
                .name("Mango Lassi")
                .description("Yogurt-based drink with fresh mango flavor")
                .price(80.0)
                .category("Beverage")
                .build()
        };
        
        for (MenuItem item : menuItems) {
            menuItemRepository.save(item);
        }
        
        System.out.println("Sample menu items loaded successfully!");
    }
}
