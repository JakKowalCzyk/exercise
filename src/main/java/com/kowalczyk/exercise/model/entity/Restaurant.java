package com.kowalczyk.exercise.model.entity;

import com.kowalczyk.exercise.enums.FoodType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by bofort on 29.09.16.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private int positionX;

    private int positionY;

    public Restaurant(String name, FoodType foodType, int positionX, int positionY) {
        this.name = name;
        this.foodType = foodType;
        this.positionX = positionX;
        this.positionY = positionY;
    }

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
