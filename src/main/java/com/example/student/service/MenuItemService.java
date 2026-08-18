package com.example.student.service;

import com.example.student.model.MenuItem;
import com.example.student.repository.MenuItemRepository;
import com.example.student.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    
    private final MenuItemRepository menuItemRepository;
    private final ReviewRepository reviewRepository;
    
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
    
    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }
    
    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuItemRepository.findByCategory(category);
    }
    
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
    
    public MenuItem updateMenuItem(Long id, MenuItem updatedItem) {
        return menuItemRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
            item.setPrice(updatedItem.getPrice());
            item.setCategory(updatedItem.getCategory());
            item.setImageUrl(updatedItem.getImageUrl());
            return menuItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("MenuItem not found"));
    }
    
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
    
    public void updateAverageRating(Long menuItemId) {
        MenuItem item = menuItemRepository.findById(menuItemId).orElseThrow();
        List<Integer> ratings = reviewRepository.findByMenuItemId(menuItemId)
                .stream()
                .map(review -> review.getRating())
                .toList();
        
        if (!ratings.isEmpty()) {
            double avg = ratings.stream().mapToInt(Integer::intValue).average().orElse(0);
            item.setAvgRating(avg);
            menuItemRepository.save(item);
        }
    }
}
