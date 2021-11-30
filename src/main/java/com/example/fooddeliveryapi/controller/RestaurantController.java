package com.example.fooddeliveryapi.controller;

import com.example.fooddeliveryapi.dto.RestaurantDto;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.RestaurantRepository;
import com.example.fooddeliveryapi.service.RestaurantService;
import com.example.fooddeliveryapi.validater.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantValidator restaurantValidator;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantValidator restaurantValidator, RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantValidator = restaurantValidator;
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(restaurantValidator);
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> showRestaurantList() {

        return restaurantRepository.findAll();
    }

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant (@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.registerRestaurant(restaurantDto);
    }
}
