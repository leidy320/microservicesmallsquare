package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EnableDisablePlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateEditRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;

import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateRequestMapper {
    Plate toPlate(PlateRequestDto plateRequestDto);
    Plate toPlate(PlateEditRequestDto plateEditRequestDto);
    Plate toPlate(EnableDisablePlateRequestDto enableDisablePlateRequestDto);
}
