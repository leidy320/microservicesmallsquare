package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EnableDisablePlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;

import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IPlateRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlateHandlerImpl implements IPlateHandler {
    private final IPlateServicePort plateServicePort;
    private final IPlateRequestMapper plateRequestMapper;

    @Override
    public void savePlate(PlateRequestDto plateRequestDto) throws ValidatePlateException {
        plateServicePort.savePlate(plateRequestMapper.toPlate(plateRequestDto));
    }
    @Override
    public void editPlate(PlateEditRequestDto plateEditRequestDto) throws ValidatePlateException {
       plateServicePort.editPlate(plateRequestMapper.toPlate(plateEditRequestDto));
    }

    @Override
    public void editStatusPlate(EnableDisablePlateRequestDto enableDisablePlateRequestDto) throws ValidatePlateException {
        plateServicePort.editStatusPlate(plateRequestMapper.toPlate(enableDisablePlateRequestDto));
    }

}
