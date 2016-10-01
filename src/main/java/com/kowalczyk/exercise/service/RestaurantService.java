package com.kowalczyk.exercise.service;

import com.kowalczyk.exercise.dto.RestaurantDTO;
import com.kowalczyk.exercise.dto.UserPointDTO;
import com.kowalczyk.exercise.enums.FoodType;
import com.kowalczyk.exercise.model.dao.RestaurantDao;
import com.kowalczyk.exercise.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JK on 2016-09-30.
 */
@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;
    private UserPointDTO userPointDTO;

    public void setUserPointDTO(UserPointDTO userPointDTO) {
        this.userPointDTO = userPointDTO;
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantDao.saveRestaurant(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantDao.getAllRestaurants();
    }

    public List<Restaurant> getRestaurantsByFoodType(FoodType foodType) {
        return restaurantDao.getRestaurantsByFoodType(foodType);
    }

    public List<RestaurantDTO> createRestauarantsDTOs() {
        List<RestaurantDTO> restaurantDTOs = getAllRestaurants().stream().map(restaurant -> makeRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantDTOs;
    }

    public List<RestaurantDTO> sortRestaurantsDTOs(){
        List<RestaurantDTO> restaurantDTOList = this.createRestauarantsDTOs();
        Collections.sort(restaurantDTOList, (o1, o2) -> o1.getDistance() - o2.getDistance());
        return restaurantDTOList;
    }

    private RestaurantDTO makeRestaurantDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getName(), restaurant.getFoodType(),
                countTwoPointsDistance(userPointDTO.getX(), userPointDTO.getY(), restaurant.getPositionX(), restaurant.getPositionY()));
    }

    public int countTwoPointsDistance(int xa, int ya, int xb, int yb) {
        double first = Math.pow(getBSubtractA(xa, xb), getBSubtractA(xa, xb));
        double second = Math.pow(getBSubtractA(ya, yb), getBSubtractA(ya, yb));
        return (int) Math.sqrt(first + second);
    }

    private double getBSubtractA(int a, int b) {
        return b - a;
    }
}
