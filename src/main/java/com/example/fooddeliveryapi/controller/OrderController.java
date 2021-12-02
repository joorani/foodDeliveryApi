package com.example.fooddeliveryapi.controller;


import com.example.fooddeliveryapi.dto.OrderRequestDto;
import com.example.fooddeliveryapi.dto.OrderResponseDto;
import com.example.fooddeliveryapi.model.OrderEntity;
import com.example.fooddeliveryapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 요청
    @PostMapping("/order/request")
    public OrderResponseDto orderFood(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.orderFood(orderRequestDto);
    }

    // 주문 조회
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrderList() {

        return orderService.getOrderList();

    }
}
