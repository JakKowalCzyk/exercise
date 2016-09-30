package com.kowalczyk.exercise.dao;

import com.kowalczyk.exercise.entity.Restaurant;
import com.kowalczyk.exercise.enums.FoodType;

import java.util.List;

/**
 * Created by bofort on 29.09.16.
 */
public interface RestaurantDao {

    void saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurantsByFoodType(FoodType foodType);
}
