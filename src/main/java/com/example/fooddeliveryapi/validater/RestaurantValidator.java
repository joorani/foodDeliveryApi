package com.example.fooddeliveryapi.validater;

import com.example.fooddeliveryapi.dto.RestaurantRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator{


    public static void validateRestaurantRegister(RestaurantRequestDto restaurantRequestDto) {
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
    }
}

