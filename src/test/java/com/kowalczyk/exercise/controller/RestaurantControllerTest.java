package com.kowalczyk.exercise.controller;

import com.kowalczyk.exercise.dto.RestaurantDTO;
import com.kowalczyk.exercise.enums.FoodType;
import com.kowalczyk.exercise.model.entity.Restaurant;
import com.kowalczyk.exercise.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by JK on 2016-10-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dispatcher-servlet-test.xml"})
public class RestaurantControllerTest {

    @InjectMocks
    @Autowired
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

    private MockMvc mockMvc;

    private List<Restaurant> restaurants;
    private  List<RestaurantDTO> restaurantDTOs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
        restaurants = new ArrayList<>();
        restaurantDTOs = new ArrayList<>();
    }

    @Test
    public void testGetClosestRestaurants() throws Exception {
        Mockito.doNothing().when(restaurantService).setUserPointDTO(Mockito.any());
        Mockito.when(restaurantService.sortRestaurantsDTOs()).thenReturn(restaurantDTOs);
        mockMvc.perform(get("/restaurants/{x}/{y}", 1, 2))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRestaurantByFoodType() throws Exception {
        Mockito.when(restaurantService.getRestaurantsByFoodType(Mockito.any())).thenReturn(restaurants);
        mockMvc.perform(get("/restaurants/{foodType}", FoodType.BREAKFAST))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRestaurants() throws Exception {
        Mockito.when(restaurantService.getAllRestaurants()).thenReturn(restaurants);
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk());
    }
}