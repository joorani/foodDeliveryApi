package com.example.fooddeliveryapi.controller;

import com.example.fooddeliveryapi.dto.FoodDto;
import com.example.fooddeliveryapi.model.Food;
import com.example.fooddeliveryapi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 음식리스트 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtoList) {
       foodService.addFoods(restaurantId, foodDtoList);
    }

    //메뉴판 조회
    @GetMapping("restaurant/{restaurantId}/foods")
    public List<Food> getMenus(@PathVariable Long restaurantId) {
        return foodService.getMenus(restaurantId);
    }

}
