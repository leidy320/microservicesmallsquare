package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRestaurantRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.spi.IUserRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRestaurantMysqlAdapter implements IUserRestaurantPersistencePort{

    private final IUserRestaurantRepository userRestaurantRepository;
    private final IUserRestaurantEntityMapper userRestaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto) throws ValidateRestaurantException {
        var restaurantentity = restaurantRepository.findByIdAndIdOwner(employeRestaurantRequestDto.getIdRestaurant(),
                employeRestaurantRequestDto.getIdOwner().toString());

        if(restaurantentity == null){
            throw new ValidateRestaurantException("No existe un restaurante asociado a ese id de propietario");
        }
        userRestaurantRepository.save(userRestaurantEntityMapper.toEntity(employeRestaurantRequestDto));
    }
}
