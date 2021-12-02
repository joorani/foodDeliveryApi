package com.example.fooddeliveryapi.service;

import com.example.fooddeliveryapi.dto.RestaurantRequestDto;
import com.example.fooddeliveryapi.dto.RestaurantResponseDto;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.RestaurantRepository;
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

        if(restaurantRequestDto.getMinOrderPrice() < 1000 || restaurantRequestDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("최소 1000원, 최대 100000원 사이로 입력하세요.");
        }

        if(restaurantRequestDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("최소주문 금액은 100원 단위로만 입력 가능합니다.");
        }

        //기본 배달비(deliveryFee) 허용값: 0~10,0000원
        if (restaurantRequestDto.getDeliveryFee() < 0 || restaurantRequestDto.getDeliveryFee() > 10000) {
           throw new IllegalArgumentException("0~10000원 사이로 입력하세요.");
        }

        if(restaurantRequestDto.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("기본 배달비는 500원 단위로만 입력 가능합니다.");
        }

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
