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


//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Food food;

//    @ManyToOne
//    @JoinColumn(name = "ORDER_ID")
//    private OrderEntity orderEntity;

//    @OneToOne
//    @JoinColumn(name = "RESTAURANT_ID")
//    private Restaurant restaurant;


    public FoodOrderEntity(String name,int quantity,int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price * quantity;
    }
}
