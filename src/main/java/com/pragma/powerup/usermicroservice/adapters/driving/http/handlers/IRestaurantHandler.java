package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;


public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto personRequestDto, String token) throws ValidateRestaurantException;
    void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto, String token) throws ValidateRestaurantException;
}
