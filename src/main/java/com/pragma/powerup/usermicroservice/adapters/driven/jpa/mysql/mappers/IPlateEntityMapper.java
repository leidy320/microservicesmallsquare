package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateEntityMapper {

    //@Mapping(target = "category.id", source = "category.id")
    //@Mapping(target = "restaurant.id", source = "restaurant.id")
    PlateEntity toEntity(Plate plate);
    //@Mapping(target = "category.id", source = "category.id")
    //@Mapping(target = "restaurant.id", source = "restaurant.id")
    List<Plate> toListPlate(List<PlateEntity> Plates);
}

