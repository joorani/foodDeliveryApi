package com.example.fooddeliveryapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodOrderResponseDto> FoodOrdersInfo;
    private int deliveryFee;
    private int totalPrice;


    public OrderResponseDto(String restaurantName,
                            List<FoodOrderResponseDto> foodOrdersInfo,
                            int deliveryFee,
                            int totalPrice) {
        this.restaurantName = restaurantName;
        FoodOrdersInfo = foodOrdersInfo;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }

}
