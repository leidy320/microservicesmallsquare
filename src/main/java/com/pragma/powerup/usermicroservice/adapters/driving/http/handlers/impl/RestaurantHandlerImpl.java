package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeRestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.ITokenUtils;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.configuration.security.TokenUtilsImpl;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.apirest.IUserRestTemplate;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.ConstantString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {


    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IUserRestTemplate userRestTemplate;
    private final ITokenUtils tokenUtils;
    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token) throws ValidateRestaurantException {

        String role = userRestTemplate.getRoleByIdUSer(restaurantRequestDto.getIdOwner(), token);
        if(role.equals(Constants.OWNER)) {
            restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));
        }
        else{
            throw new ValidateRestaurantException("Solo el propietario puede crear un restaurante");
        }
    }

    @Override
    public void addEmployeToRestaurant(EmployeRestaurantRequestDto employeRestaurantRequestDto, String token) throws ValidateRestaurantException {
        String idUSer = tokenUtils.getIdByToken(token);
        employeRestaurantRequestDto.setIdOwner(Long.valueOf(idUSer));
        String role = userRestTemplate.getRoleByIdUSer(employeRestaurantRequestDto.getIdUser().toString(), token);
        if(role.equals(Constants.EMPLOYE)) {
            restaurantServicePort.addEmployeToRestaurant(employeRestaurantRequestDto);
        }
        else  {
            throw new ValidateRestaurantException("El usuario con este id no existe");

        }
    }


}
