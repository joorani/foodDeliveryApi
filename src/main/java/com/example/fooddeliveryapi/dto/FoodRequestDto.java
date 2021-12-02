package com.example.fooddeliveryapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodRequestDto {
    private String name;
    private int price;

    @Builder
    public FoodRequestDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public FoodRequestDto toEntity(){
        return FoodRequestDto.builder()
                .name(name)
                .price(price)
                .build();
    }
}
