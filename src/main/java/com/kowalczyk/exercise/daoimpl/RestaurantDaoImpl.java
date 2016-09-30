package com.kowalczyk.exercise.daoimpl;

import com.kowalczyk.exercise.dao.RestaurantDao;
import com.kowalczyk.exercise.entity.Restaurant;
import com.kowalczyk.exercise.enums.FoodType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by bofort on 29.09.16.
 */
public class RestaurantDaoImpl implements RestaurantDao {

    private static final String getAllRestaurants = "SELECT o FROM restaurant o";
    private static final String getRestaurantsByFoodSql = "SELECT o FROM restaurant o WHERE o.foodType = :foodType";

    @PersistenceContext
    private EntityManager entityManager;

    public void saveRestaurant(Restaurant restaurant) {
        entityManager.persist(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return entityManager.createQuery(getAllRestaurants, Restaurant.class).getResultList();
    }

    public List<Restaurant> getRestaurantsByFoodType(FoodType foodType) {
        TypedQuery typedQuery = entityManager.createQuery(getRestaurantsByFoodSql, Restaurant.class);
        typedQuery.setParameter("foodType", foodType);
        List<Restaurant> restaurants = typedQuery.getResultList();
        return restaurants;
    }
}
