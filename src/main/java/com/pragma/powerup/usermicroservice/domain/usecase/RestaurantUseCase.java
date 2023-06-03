package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserRestaurantPersistencePort;


import java.util.List;

public class RestaurantUseCase  implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserRestaurantPersistencePort userRestaurantPersistencePort;
    private final int MAX_CHARACTER = 13;
    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserRestaurantPersistencePort userRestaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userRestaurantPersistencePort = userRestaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) throws ValidateRestaurantException {
        validatePhone(restaurant.getPhone());
        validateNit(restaurant.getNit());
        validateName(restaurant.getName());
        restaurantPersistencePort.saveRestaurant(restaurant);

    }

    @Override
    public void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto) throws ValidateRestaurantException {
        userRestaurantPersistencePort.addEmployeToRestaurant(employeRestaurantRequestDto);
    }

    @Override
    public List<Restaurant> getRestaurant(int page, int pageSize) throws ValidateRestaurantException {

        return restaurantPersistencePort.getRestaurant(page, pageSize);
    }

    protected void validateNit(String nit) throws ValidateRestaurantException {
        if(nit.matches(".*[a-zA-Z]+.*")) {
            throw new ValidateRestaurantException("El Campo nit  solo acepta numeros");
        }
    }

    protected void validatePhone(String phone) throws ValidateRestaurantException {

        if(phone.length() >= MAX_CHARACTER || phone.matches(".*[a-zA-Z]+.*")) {
            throw new ValidateRestaurantException("El numero de telefono no debe superar los 13 caracteres, ni debe contener letras");
        }
    }

    protected void validateName(String name) throws ValidateRestaurantException {

        if(!name.matches(".*[a-zA-Z]+.*")) {
            throw new ValidateRestaurantException("El nombre no debe contener solo numeros");
        }
    }

}
