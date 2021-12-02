package com.example.fooddeliveryapi.dto;

import com.example.fooddeliveryapi.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Builder
public class RestaurantRequestDto{

    private String name;
    private int minOrderPrice;
    private int deliveryFee;


    public RestaurantRequestDto(String name, int minOrderPrice, int deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

}