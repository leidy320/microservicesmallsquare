package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EnableDisablePlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;

import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.ITokenUtils;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IPlateRequestMapper;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.apirest.IUserRestTemplate;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlateHandlerImpl implements IPlateHandler {
    private final IPlateServicePort plateServicePort;
    private final IPlateRequestMapper plateRequestMapper;
    private final IUserRestTemplate userRestTemplate;
    private final ITokenUtils tokenUtils;
    @Override
    public void savePlate(PlateRequestDto plateRequestDto) throws ValidatePlateException, ValidateCategoryException {
        plateServicePort.savePlate(plateRequestMapper.toPlate(plateRequestDto));
    }
    @Override
    public void editPlate(PlateEditRequestDto plateEditRequestDto) throws ValidatePlateException {
       plateServicePort.editPlate(plateRequestMapper.toPlate(plateEditRequestDto));
    }

    @Override
    public void editStatusPlate(EnableDisablePlateRequestDto enableDisablePlateRequestDto, String token) throws ValidatePlateException {

        String idUSer = tokenUtils.getIdByToken(token);
        enableDisablePlateRequestDto.setId_owner(idUSer);
        String role = userRestTemplate.getRoleByIdUSer(enableDisablePlateRequestDto.getId_owner().toString(), token);
        if(role.equals(Constants.OWNER)) {
            plateServicePort.editStatusPlate(plateRequestMapper.toPlate(enableDisablePlateRequestDto));
        }
        else  {
            throw new ValidatePlateException("El usuario con este id no existe");

        }

    }

}
