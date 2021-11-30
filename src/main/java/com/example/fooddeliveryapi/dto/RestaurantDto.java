package com.example.fooddeliveryapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDto {
    @NotBlank
    private String name;
    @NotBlank
    private int minOrderPrice;
    @NotBlank
    private int deliveryFee;

}
