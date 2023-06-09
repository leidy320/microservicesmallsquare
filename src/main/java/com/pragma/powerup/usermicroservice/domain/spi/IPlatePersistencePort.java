package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.model.Plate;


public interface IPlatePersistencePort {
    void savePlate(Plate plate, Category category) throws ValidatePlateException;
    void editPlate(Plate plate) throws ValidatePlateException;
    void  editStatusPlate(Plate plate) throws ValidatePlateException;
}
