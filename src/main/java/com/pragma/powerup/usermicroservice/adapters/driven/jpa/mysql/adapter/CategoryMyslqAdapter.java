package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryMyslqAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository iCategoryRepository;
    private final ICategoryRequestMapper iCategoryRequestMapper;

    @Override
    public Category findById(Long id) throws ValidateCategoryException {
        Optional<CategoryEntity> categoryFound = iCategoryRepository.findById(id);

        if(!categoryFound.isPresent()) {
            throw new ValidateCategoryException("La categoria no existe");
        }

        return iCategoryRequestMapper.toCategory(categoryFound.get());
    }
}
