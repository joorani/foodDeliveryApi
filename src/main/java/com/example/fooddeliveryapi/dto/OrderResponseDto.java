package com.example.fooddeliveryapi.dto;

import com.example.fooddeliveryapi.model.OrderEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodOrderResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;



    public OrderResponseDto(OrderEntity orderEntity, List<FoodOrderResponseDto> foodOrderResponseDtoList) {
        this.restaurantName = orderEntity.getRestaurantName();
        this.foods= foodOrderResponseDtoList;
        this.deliveryFee = orderEntity.getDeliveryFee();
        this.totalPrice = orderEntity.getTotalPrice();
    }

}
