package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListRestaurantResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

import java.util.List;


public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto personRequestDto, String token) throws ValidateRestaurantException;
    void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto, String token) throws ValidateRestaurantException;
    List<ListRestaurantResponseDto> getRestaurant(int page, int pagesize) throws ValidateRestaurantException;
}
