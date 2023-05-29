package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;

public interface IUserRestaurantPersistencePort {
    void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto) throws ValidateRestaurantException;
}
