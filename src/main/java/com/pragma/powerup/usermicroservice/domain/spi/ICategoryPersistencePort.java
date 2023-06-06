package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.model.Category;

public interface ICategoryPersistencePort {

    Category findById(Long id) throws ValidateCategoryException;

}
