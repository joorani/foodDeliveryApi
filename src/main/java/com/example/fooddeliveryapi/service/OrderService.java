package com.example.fooddeliveryapi.service;

import com.example.fooddeliveryapi.dto.*;
import com.example.fooddeliveryapi.model.FoodOrderEntity;
import com.example.fooddeliveryapi.model.OrderEntity;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.OrderRepository;
import com.example.fooddeliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodService foodService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        FoodService foodService,
                        RestaurantRepository restaurantRepository
    ) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
        this.restaurantRepository = restaurantRepository;
    }

    //주문요청
    @Transactional
    public OrderResponseDto orderFood(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("찾는 음식점id가 없습니다."));



        List<FoodOrderEntity> foodOrderEntityList = new ArrayList<>();
        List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderRequestDto.getFoods();
        for(FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtoList) {

            if(foodOrderRequestDto.getQuantity() < 1 || foodOrderRequestDto.getQuantity() > 100) {
                throw new IllegalArgumentException("가능한 음식 주문 수량을 초과하였습니다.");
            }

            List<FoodResponseDto> foodList = foodService.getMenus(orderRequestDto.getRestaurantId());
            for(FoodResponseDto food : foodList) {

                if(!food.getId().equals(foodOrderRequestDto.getId())){
                    continue;
                }

                String foodname = food.getName();
                int quantity = foodOrderRequestDto.getQuantity();
                int price = food.getPrice() * quantity;

                FoodOrderEntity foodOrder = new FoodOrderEntity(
                        foodname,
                        foodOrderRequestDto.getQuantity(),
                        food.getPrice());

                foodOrderEntityList.add(foodOrder);

                foodOrderResponseDtoList.add(new FoodOrderResponseDto(foodOrder));
            }
        }

        //주문음식 총 가격
        int sumFoodsPrice = 0;
        for(FoodOrderResponseDto foodInfo : foodOrderResponseDtoList ) {
            sumFoodsPrice += foodInfo.getPrice();
        }

        System.out.println(sumFoodsPrice);

        if(sumFoodsPrice< restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("주문금액이 최소주문금액보다 작습니다.");
        }

        OrderEntity orderEntity = new OrderEntity(
                restaurant.getName(),
                foodOrderEntityList,
                restaurant.getDeliveryFee(),
                sumFoodsPrice + restaurant.getDeliveryFee());

        orderRepository.save(orderEntity);

        return new OrderResponseDto(orderEntity, foodOrderResponseDtoList);
    }

//    //주문 조회
    @Transactional
    public List<OrderResponseDto> getOrderList() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntityList) {
            List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
            for(FoodOrderEntity foodOrderEntity: orderEntity.getFoodOrderEntityList()) {
                FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(foodOrderEntity);
                foodOrderResponseDtoList.add(foodOrderResponseDto);
            }
            OrderResponseDto orderResponseDto = new OrderResponseDto(orderEntity, foodOrderResponseDtoList);
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}
