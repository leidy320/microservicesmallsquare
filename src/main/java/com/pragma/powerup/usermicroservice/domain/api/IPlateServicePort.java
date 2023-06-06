package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;


public interface IPlateServicePort {
    void savePlate(Plate plate) throws ValidatePlateException, ValidateCategoryException;
    void editPlate(Plate plate) throws ValidatePlateException;
    void editStatusPlate(Plate plate) throws ValidatePlateException;
}
