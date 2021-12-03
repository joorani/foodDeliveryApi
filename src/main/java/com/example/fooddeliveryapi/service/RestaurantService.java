package com.example.fooddeliveryapi.service;

import com.example.fooddeliveryapi.dto.RestaurantRequestDto;
import com.example.fooddeliveryapi.dto.RestaurantResponseDto;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.RestaurantRepository;
import com.example.fooddeliveryapi.validater.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public RestaurantResponseDto registerRestaurant(RestaurantRequestDto restaurantRequestDto) {

        //입력값 Validation
        RestaurantValidator.validateRestaurantRegister(restaurantRequestDto);

        Restaurant restaurant = restaurantRepository.save(
                Restaurant.builder()
                        .name(restaurantRequestDto.getName())
                        .minOrderPrice(restaurantRequestDto.getMinOrderPrice())
                        .deliveryFee(restaurantRequestDto.getDeliveryFee())
                        .build());

        return new RestaurantResponseDto(restaurant);
    }

    @Transactional
    public List<RestaurantResponseDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantResponseDto> restaurantList = new ArrayList<>();
        for(Restaurant restaurant: restaurants) {
            RestaurantResponseDto responseDto =  new RestaurantResponseDto(restaurant);
            restaurantList.add(responseDto);
        }

        return restaurantList;
    }

}
