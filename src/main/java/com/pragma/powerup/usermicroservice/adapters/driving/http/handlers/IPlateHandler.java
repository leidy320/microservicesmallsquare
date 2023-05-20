package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;


public interface IPlateHandler {
    void savePlate(PlateRequestDto plateRequestDto) throws ValidatePlateException;
    void editPlate(PlateEditRequestDto plateEditRequestDto) throws ValidatePlateException;
}
