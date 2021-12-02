package com.example.fooddeliveryapi.service;

import com.example.fooddeliveryapi.dto.FoodOrderRequestDto;
import com.example.fooddeliveryapi.dto.FoodOrderResponseDto;
import com.example.fooddeliveryapi.dto.OrderRequestDto;
import com.example.fooddeliveryapi.dto.OrderResponseDto;
import com.example.fooddeliveryapi.model.Food;
import com.example.fooddeliveryapi.model.FoodOrderEntity;
import com.example.fooddeliveryapi.model.OrderEntity;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodService foodService;

    @Autowired
    public OrderService(OrderRepository orderRepository, FoodService foodService) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
    }

    //주문요청
    @Transactional
    public OrderResponseDto registerOrder(OrderRequestDto orderRequestDto) {
        Restaurant orderRestaurant = orderRepository.findByRestaurantId(orderRequestDto.getRestaurantId());

        List<Food> foodList = foodService.getMenus(orderRequestDto.getRestaurantId());

        List<FoodOrderEntity> foodOrderInfoList = new ArrayList<>();
        List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderRequestDto.getFoodOrderRequestList();

        for(FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtoList) {
            if(foodOrderRequestDto.getQuantity() < 1 || foodOrderRequestDto.getQuantity() > 100) {
                throw new IllegalArgumentException("가능한 음식 주문 수량을 초과하였습니다.");
            }

            for(Food food: foodList) {
                if(!food.getId().equals(foodOrderRequestDto.getId())){
                    continue;
                }

                String foodname = food.getName();
                int quantity = foodOrderRequestDto.getQuantity();
                int price = food.getPrice() * quantity;

                FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(foodname, price, quantity);
                foodOrderResponseDtoList.add(foodOrderResponseDto);

                FoodOrderEntity foodOrder = new FoodOrderEntity(foodOrderResponseDto);
                foodOrderInfoList.add(foodOrder);

            }

        }

        //주문음식 총 가격
        int sumFoodsPrice = 0;
        for(FoodOrderResponseDto foodInfo : foodOrderResponseDtoList ) {
            sumFoodsPrice += foodInfo.getPrice();
            System.out.println(sumFoodsPrice);
        }

        System.out.println(sumFoodsPrice);

        OrderResponseDto orderDto = new OrderResponseDto(
                orderRestaurant.getName(),
                foodOrderResponseDtoList,
                orderRestaurant.getDeliveryFee(),
                sumFoodsPrice);

        OrderEntity orderEntity = new OrderEntity(
                orderRestaurant.getName(),
                        foodOrderInfoList,
                        orderRestaurant.getDeliveryFee(),
                        sumFoodsPrice);

        orderRepository.save(orderEntity);
        return orderDto;
    }

    //주문 조회
    @Transactional
    public List<OrderResponseDto> getOrderList() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntityList) {
            List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
            for(FoodOrderEntity foodOrder: orderEntity.getFoodOrderEntityList()) {
                String foodName = foodOrder.getName();
                int quantity = foodOrder.getQuantity();
                int foodPrice = foodOrder.getPrice() * quantity;

                FoodOrderResponseDto foodOrderResponseDto =
                        new FoodOrderResponseDto(foodName, quantity, foodPrice);
                foodOrderResponseDtoList.add(foodOrderResponseDto);
            }

           OrderResponseDto orderResponseDto =  new OrderResponseDto(
                    orderEntity.getRestaurantName(),
                    foodOrderResponseDtoList,
                    orderEntity.getDeliveryFee(),
                    orderEntity.getTotalPrice()
            );
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}
