package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

import java.util.List;


public interface IPlatePersistencePort {
    void savePlate(Plate plate, Category category) throws ValidatePlateException;
    void editPlate(Plate plate) throws ValidatePlateException;
    void  editStatusPlate(Plate plate) throws ValidatePlateException;
    List<Plate> getPlate(int page, int pageSize, Long idCategory, Long idRestaurant ) throws ValidatePlateException;
}
