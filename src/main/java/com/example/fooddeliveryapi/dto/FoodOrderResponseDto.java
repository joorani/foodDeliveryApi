package com.example.fooddeliveryapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodOrderResponseDto {
    private String name;
    private int quantity;
    private int price;

    public FoodOrderResponseDto(String name, int foodOrderPrice, int foodOrderQuantity) {
        this.name = name;
        this.price = foodOrderPrice;
        this.quantity = foodOrderQuantity;
    }
}
