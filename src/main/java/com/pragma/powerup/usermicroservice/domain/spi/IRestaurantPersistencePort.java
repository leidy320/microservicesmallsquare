package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getRestaurant(int page, int pageSize) throws ValidateRestaurantException;
    //Restaurant GetRestaurantByIdAndIdOwner(Long id, Long id_owner);
}
