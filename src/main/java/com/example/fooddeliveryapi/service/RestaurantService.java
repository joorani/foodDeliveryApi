package com.example.fooddeliveryapi.service;

import com.example.fooddeliveryapi.dto.RestaurantDto;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.RestaurantRepository;
import com.example.fooddeliveryapi.validater.RestaurantValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantValidator restaurantValidator;


    @Transactional
    public Restaurant registerRestaurant(RestaurantDto restaurantDto) {
        if (restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("최소 1000원, 최대 100000원 사이로 입력하세요.");
        }

        if(restaurantDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("최소주문 금액은 100원 단위로만 입력 가능합니다.");
        }

        //기본 배달비(deliveryFee) 허용값: 0~10,0000원
        if (restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10000) {
           throw new IllegalArgumentException("0~10000원 사이로 입력하세요.");
        }

        if(restaurantDto.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("기본 배달비는 500원 단위로만 입력 가능합니다.");
        }

        Restaurant newRestaurant = new Restaurant(restaurantDto);
        return restaurantRepository.save(newRestaurant);

    }
}
