package com.kowalczyk.exercise.entity;

import com.kowalczyk.exercise.enums.FoodType;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by bofort on 29.09.16.
 */
@Entity
public class Restaurant {
    @Id
    private int id;

    private String name;

    private FoodType foodType;

    private int positionX;

    private int positionY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
