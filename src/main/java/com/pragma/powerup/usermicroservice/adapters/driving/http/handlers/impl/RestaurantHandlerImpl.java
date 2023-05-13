package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.apirest.IUserRestTemplate;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {
    public static final String OWNER = "ROLE_OWNER";
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IUserRestTemplate userRestTemplate;
    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token) throws ValidateRestaurantException {

        String role = userRestTemplate.getRoleByIdUSer(restaurantRequestDto.getIdOwner(), token);
        if(role.equals(OWNER)) {
            restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));
        }
        else{
            throw new ValidateRestaurantException("Solo el propietario puede crear un restaurante");
        }
    }


}
