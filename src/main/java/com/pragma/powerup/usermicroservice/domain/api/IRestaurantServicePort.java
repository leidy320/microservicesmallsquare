package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant) throws ValidateRestaurantException;
    void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto) throws ValidateRestaurantException;
}
