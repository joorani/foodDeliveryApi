package com.example.fooddeliveryapi.model;

import com.example.fooddeliveryapi.dto.FoodOrderRequestDto;
import com.example.fooddeliveryapi.dto.FoodOrderResponseDto;
import com.example.fooddeliveryapi.dto.OrderResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class FoodOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity orderEntity;

    public FoodOrderEntity(FoodOrderResponseDto foodInfo) {
        this.name = foodInfo.getName();
        this.quantity = foodInfo.getQuantity();
        this.price = foodInfo.getPrice();
    }
}
