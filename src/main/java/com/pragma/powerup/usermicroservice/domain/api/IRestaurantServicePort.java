package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant) throws ValidateRestaurantException;
}
