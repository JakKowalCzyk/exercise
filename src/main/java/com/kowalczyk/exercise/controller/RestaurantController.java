package com.kowalczyk.exercise.controller;

import com.kowalczyk.exercise.dto.UserPointDTO;
import com.kowalczyk.exercise.enums.FoodType;
import com.kowalczyk.exercise.model.entity.Restaurant;
import com.kowalczyk.exercise.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JK on 2016-10-01.
 */
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @RequestMapping(path = "/restaurants", method = RequestMethod.POST)
    public ResponseEntity addRestaurant(@RequestBody Restaurant restaurant){
        restaurantService.saveRestaurant(restaurant);
        return new ResponseEntity(restaurant, HttpStatus.OK);
    }

    @RequestMapping(path = "/restaurants/{x}/{y}", method = RequestMethod.GET)
    public ResponseEntity getClosestRestaurants(@PathVariable int x, @PathVariable int y){
        UserPointDTO userPointDTO = new UserPointDTO(x, y);
        restaurantService.setUserPointDTO(userPointDTO);
        return new ResponseEntity(restaurantService.sortRestaurantsDTOs(), HttpStatus.OK);
    }

    @RequestMapping(path = "restaurants/{foodType}", method = RequestMethod.GET)
    public ResponseEntity getRestaurantByFoodType(@PathVariable FoodType foodType){
        return new ResponseEntity(restaurantService.getRestaurantsByFoodType(foodType), HttpStatus.OK);
    }
}
