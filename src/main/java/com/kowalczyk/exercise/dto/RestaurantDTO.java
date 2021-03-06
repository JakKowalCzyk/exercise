package com.kowalczyk.exercise.dto;

import com.kowalczyk.exercise.enums.FoodType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by JK on 2016-09-30.
 */
public class RestaurantDTO {

    private String name;

    private FoodType foodType;

    private double distance;

    public RestaurantDTO(String name, FoodType foodType, double distance) {
        this.name = name;
        this.foodType = foodType;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
