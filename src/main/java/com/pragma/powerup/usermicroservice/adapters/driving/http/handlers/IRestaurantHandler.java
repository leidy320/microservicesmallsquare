package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;


public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto personRequestDto) throws ValidateRestaurantException;
}
