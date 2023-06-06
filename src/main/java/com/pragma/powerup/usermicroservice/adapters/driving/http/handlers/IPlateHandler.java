package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EnableDisablePlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListPlateResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListRestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;

import java.util.List;


public interface IPlateHandler {
    void savePlate(PlateRequestDto plateRequestDto) throws ValidatePlateException, ValidateCategoryException;
    void editPlate(PlateEditRequestDto plateEditRequestDto) throws ValidatePlateException;
    void editStatusPlate(EnableDisablePlateRequestDto enableDisablePlateRequestDto, String token) throws ValidatePlateException;
    List<ListPlateResponseDto> getPlate(int page, int pagesize, Long idCategory) throws ValidatePlateException, ValidateCategoryException;
}
