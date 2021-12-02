package com.example.fooddeliveryapi.validater;

import com.example.fooddeliveryapi.dto.RestaurantDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RestaurantValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return RestaurantDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        RestaurantDto restaurantDto = (RestaurantDto) obj;

        //음식점 이름 빈칸 확인
        ValidationUtils.rejectIfEmpty(e, "name",
                "Restaurant.name empty",
                "음식점 이름을 입력해주세요.");

        //최소주문 확인 허용값 1,000~100,000
        ValidationUtils.rejectIfEmpty(e,"minOrderPrice", "Restaurant.minOrderPrice empty",
                "최소주문 가격을 입력해주세요.");

        if(restaurantDto.getMinOrderPrice() < 1000) {
            e.rejectValue("minOrderPrice", "minOrderPrice invalid","최소주문 금액은 1,000원 이상이어야 합니다." );
        } else if (restaurantDto.getMinOrderPrice() > 100000) {
            e.rejectValue("minOrderPrice", "minOrderPrice invalid","최소주문 금액은 1,000,000을 초과할 수 없습니다." );
        } else if(restaurantDto.getMinOrderPrice() % 100 != 0) {
            e.rejectValue("minOrderPrice", "minOrderPrice invalid","최소주문 금액은 100원 단위로만 입력 가능합니다." );
        }

        //기본 배달비(deliveryFee) 허용값: 0~10,0000원
        ValidationUtils.rejectIfEmpty(e, "deliveryFee", "Restaurant.deliveryFee empty", "기본 배달비를 입력해주세요.");

        if (restaurantDto.getDeliveryFee() < 0) {
            e.rejectValue("deliveryFee", "deliveryFee invalid", "기본 배달비는 0원 이상부터 입력 가능합니다.");
        } else if(restaurantDto.getDeliveryFee() > 10000) {
            e.rejectValue("deliveryFee", "deliveryFee invalid", "기본 배달비는 10000원을 초과할 수 없습니다.");
        } else if(restaurantDto.getDeliveryFee() % 500 != 0) {
            e.rejectValue("deliveryFee", "deliveryFee invalid", "기본 배달비는 500원 단위로만 입력 가능합니다.");
        }

    }
}
