package com.example.fooddeliveryapi.repository;

import com.example.fooddeliveryapi.model.FoodOrderEntity;
import com.example.fooddeliveryapi.model.OrderEntity;
import com.example.fooddeliveryapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Restaurant findByRestaurantId(Long restaurantId);

}
