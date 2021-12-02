package com.example.fooddeliveryapi.controller;

import com.example.fooddeliveryapi.dto.RestaurantRequestDto;
import com.example.fooddeliveryapi.dto.RestaurantResponseDto;
import com.example.fooddeliveryapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    //음식점 등록
    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(
            @RequestBody RestaurantRequestDto restaurantRequestDto)
    {
        return restaurantService.registerRestaurant(restaurantRequestDto);
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurans() {

        return restaurantService.getRestaurants();
    }

}
