package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getRestaurant(int page, int pageSize) throws ValidateRestaurantException;
}
