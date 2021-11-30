package com.example.fooddeliveryapi.repository;

import com.example.fooddeliveryapi.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByNameAndRestaurantId(String name, Long restaurantId);

    List<Food> findAllByRestaurantId(Long restaurantId);
}
