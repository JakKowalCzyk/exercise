package com.kowalczyk.exercise.service;

import com.kowalczyk.exercise.dto.RestaurantDTO;
import com.kowalczyk.exercise.dto.UserPointDTO;
import com.kowalczyk.exercise.enums.FoodType;
import com.kowalczyk.exercise.model.daoimpl.RestaurantDaoImpl;
import com.kowalczyk.exercise.model.entity.Restaurant;
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

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by JK on 2016-10-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dispatcher-servlet-test.xml"})
public class RestaurantServiceTest {

    private UserPointDTO userPointDTO;

    @Mock
    private EntityManager entityManager;


    @Mock
    private RestaurantDaoImpl restaurantDao;

    @InjectMocks
    @Autowired
    private RestaurantService restaurantService;

    private List<Restaurant> restaurantList;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        createRestaurantList();
        Mockito.when(restaurantDao.getAllRestaurants()).thenReturn(restaurantList);
        userPointDTO = new UserPointDTO(2, 4);
        restaurantService.setUserPointDTO(userPointDTO);
    }

    private void createRestaurantList() {
        Restaurant restaurant1 = new Restaurant("test", FoodType.BREAKFAST, 2, 3 );
        Restaurant restaurant2 = new Restaurant("test2", FoodType.BREAKFAST, 3, 3 );
        Restaurant restaurant3 = new Restaurant("test3", FoodType.DINNER, 2, 4 );
        Restaurant restaurant4 = new Restaurant("test4", FoodType.DINNER, 6, 5 );
        Restaurant restaurant5 = new Restaurant("test5", FoodType.FASTFOOD, 6, 3 );
        Restaurant restaurant6 = new Restaurant("test6", FoodType.FASTFOOD, 6, 6 );
        Restaurant restaurant7 = new Restaurant("test7", FoodType.HOOMEFOOD , 43, 4 );
        Restaurant restaurant8 = new Restaurant("test8", FoodType.LUNCH, 62, 35 );
        Restaurant restaurant9 = new Restaurant("test9", FoodType.MEET, 24, 43 );
        Restaurant restaurant10 = new Restaurant("test20", FoodType.BREAKFAST, 32, 13 );
        restaurantList = new ArrayList<>();
        restaurantList.addAll(Arrays.asList(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5, restaurant6,
                restaurant7, restaurant8, restaurant9, restaurant10));
    }

    @Test
    public void testSetUserPointDTO() throws Exception {
        restaurantService.setUserPointDTO(userPointDTO);
        assertEquals(2, userPointDTO.getX());
        assertEquals(4, userPointDTO.getY());
    }

    @Test
    public void testGetBSubtractA() {
        assertEquals(5, restaurantService.getBSubtractA(5, 10));
        assertEquals(150, restaurantService.getBSubtractA(155, 305));
        assertNotEquals(10000, restaurantService.getBSubtractA(-2000, -3000));
    }

    @Test
    public void testCountTwoPointsDistance() {
        assertThat(1.0, equalTo(restaurantService.countTwoPointsDistance(5, 3, 5, 4)));
        assertThat(41.10960958218893, equalTo(restaurantService.countTwoPointsDistance(7, 9, 10, 50)));
        assertNotSame(1, restaurantService.countTwoPointsDistance(5, 3, 5, 4));
    }

    @Test
    public void testGetAllRestaurants(){
        Mockito.when(restaurantDao.getAllRestaurants()).thenReturn(restaurantList);
        List<Restaurant> newList = restaurantService.getAllRestaurants();
        assertEquals(restaurantList.size(), newList.size());
    }

    @Test
    public void testGetRestaurantsByFoodType() {
        List<Restaurant> breakfastFilteredList = restaurantList.stream().filter(food -> food.getFoodType().equals(FoodType.BREAKFAST)).collect(Collectors.toList());
        Mockito.when(restaurantDao.getRestaurantsByFoodType(FoodType.BREAKFAST)).
                thenReturn(breakfastFilteredList);
        List<Restaurant> breakfastList = restaurantService.getRestaurantsByFoodType(FoodType.BREAKFAST);
        assertEquals(breakfastFilteredList.size(), breakfastList.size());
        assertEquals(breakfastFilteredList.get(0).getFoodType(), breakfastList.get(0).getFoodType());
    }

    @Test
    public void testCreateRestaurantsDTOs(){
        List<RestaurantDTO> restaurantDTOs = restaurantService.createRestaurantsDTOs();
        assertEquals(restaurantList.size(), restaurantDTOs.size());
        assertThat(1.0, equalTo(restaurantDTOs.get(0).getDistance()));
        assertEquals(restaurantList.get(0).getName(), restaurantDTOs.get(0).getName());
        assertEquals(restaurantList.get(7).getFoodType(), restaurantDTOs.get(7).getFoodType());
    }

    @Test
    public void testSortRestaurantsDTOs() {
        List<RestaurantDTO> restaurantDTOs = restaurantService.sortRestaurantsDTOs();
        assertEquals(restaurantList.size(), restaurantDTOs.size());
        assertThat(0.0, equalTo(restaurantDTOs.get(0).getDistance()));
        assertThat(restaurantDTOs.get(1).getDistance(), greaterThan(restaurantDTOs.get(0).getDistance()));
        assertThat(restaurantDTOs.get(9).getDistance(), greaterThan(restaurantDTOs.get(8).getDistance()));

    }

    @Test
    public void testMakeRestaurantDTO() {
        Restaurant restaurant = new Restaurant("Restauracja", FoodType.SOUPS, 10, 40);
        RestaurantDTO restaurantDTO = restaurantService.convertRestaurantToRestaurantDTO(restaurant);
        assertEquals(restaurant.getName(), restaurantDTO.getName());
        assertEquals(restaurant.getFoodType(), restaurantDTO.getFoodType());
        assertThat(restaurantService.countTwoPointsDistance(userPointDTO.getX(), userPointDTO.getY(), restaurant.getPositionX()
                , restaurant.getPositionY()), equalTo(restaurantDTO.getDistance()));
    }



}