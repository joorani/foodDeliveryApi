package com.example.fooddeliveryapi.service;

import com.example.fooddeliveryapi.dto.FoodRequestDto;
import com.example.fooddeliveryapi.dto.FoodResponseDto;
import com.example.fooddeliveryapi.model.Food;
import com.example.fooddeliveryapi.model.Restaurant;
import com.example.fooddeliveryapi.repository.FoodRepository;
import com.example.fooddeliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // 음식 리스트 저장
    @Transactional
    public void addFoods(Long restaurantId, List<FoodRequestDto> foodRequestDtoList) {

        //음식점 있는지 확인.
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점 ID 입니다.")
        );

        //음식이름 중복 확인
        for(FoodRequestDto foodRequestDto : foodRequestDtoList) {
            if(foodRepository.existsByNameAndRestaurant_Id(foodRequestDto.getName(), restaurantId)) {
                throw new IllegalArgumentException("중복된 음식이름이 존재합니다.");
            }

            //가격 허용값 100원 ~ 1,000,000원, 100원 단위로 입력 가능, 아니면 에러 발생./
            if(foodRequestDto.getPrice() < 100 || foodRequestDto.getPrice() > 1000000) {
                throw new IllegalArgumentException("음식가격은 100 ~ 1,000,000 사이로 입력하세요.");
            } else if(foodRequestDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로만 입력 가능합니다.");
            }

            Food food = new Food(restaurant, foodRequestDto);
            foodRepository.save(food);
        }
    }

    //메뉴판 조회
    @Transactional
    public List<FoodResponseDto> getMenus(Long restaurantId) {
        List<Food> foods = foodRepository.findAllByRestaurantId(restaurantId);
        List<FoodResponseDto> foodsList = new ArrayList<>();

        for(Food food : foods) {
            FoodResponseDto foodResponseDto = new FoodResponseDto(food);
            foodsList.add(foodResponseDto);
        }
        return foodsList;
    }
}
