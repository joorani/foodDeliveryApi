package com.example.fooddeliveryapi.dto;

import com.example.fooddeliveryapi.model.Food;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;

    public FoodResponseDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
    }
}
