package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EnableDisablePlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListPlateResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListRestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateRequestMapper {
    Plate toPlate(PlateRequestDto plateRequestDto);
    Plate toPlate(PlateEditRequestDto plateEditRequestDto);
    Plate toPlate(EnableDisablePlateRequestDto enableDisablePlateRequestDto);
    List<ListPlateResponseDto> toListPlateResponseDto(List<Plate> listplate);
}
