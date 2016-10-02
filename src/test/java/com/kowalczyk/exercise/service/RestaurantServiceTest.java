package com.kowalczyk.exercise.service;

import com.kowalczyk.exercise.dto.UserPointDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by JK on 2016-10-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/WEB-INF/.xml"})
public class RestaurantServiceTest {

    private UserPointDTO userPointDTO;
    @Autowired
    private RestaurantService restaurantService;

    @Before
    public void before(){
        userPointDTO = new UserPointDTO(2, 4);
    }


    @Test
    public void testSetUserPointDTO() throws Exception {
        restaurantService.setUserPointDTO(userPointDTO);
        assertEquals(userPointDTO.getX(), 2);
        assertEquals(userPointDTO.getY(), 4);
    }

}