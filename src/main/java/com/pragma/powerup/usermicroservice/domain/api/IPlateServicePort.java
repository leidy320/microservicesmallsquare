package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

import java.util.List;


public interface IPlateServicePort {
    void savePlate(Plate plate) throws ValidatePlateException, ValidateCategoryException;
    void editPlate(Plate plate) throws ValidatePlateException;
    void editStatusPlate(Plate plate) throws ValidatePlateException;
    List<Plate> getPlate(int page, int pageSize, Long idCategory, Long idRestaurant) throws ValidatePlateException, ValidateCategoryException;
}
