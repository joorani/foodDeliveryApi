package com.example.fooddeliveryapi.dto;

import com.example.fooddeliveryapi.model.FoodOrderEntity;
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

    public FoodOrderResponseDto(FoodOrderEntity entity) {
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
    }
}
