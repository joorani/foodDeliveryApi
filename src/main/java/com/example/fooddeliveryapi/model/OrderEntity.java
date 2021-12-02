package com.example.fooddeliveryapi.model;

import com.example.fooddeliveryapi.dto.FoodOrderResponseDto;
import com.example.fooddeliveryapi.dto.OrderResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class OrderEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String restaurantName;

    private int totalPrice;

    private int deliveryFee;

    @OneToMany
    @JoinColumn(name = "FOOD_ORDER_ID")
    private List<FoodOrderEntity> foodOrderEntityList;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "Food_ID")
    private Food food;

    public OrderEntity(String restaurantName,
                       List<FoodOrderEntity> foodOrderEntityList,
                       int deliveryFee,
                       int totalPrice
    ) {
        this.restaurantName = restaurantName;
        this.foodOrderEntityList = foodOrderEntityList;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
