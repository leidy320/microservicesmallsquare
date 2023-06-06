package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class PlateMyslqAdapter implements IPlatePersistencePort {
    private final IPlateRepository plateRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final ICategoryRequestMapper categoryRequestMapper;


    @Override
    public void savePlate(Plate plate, Category category) throws ValidatePlateException {
        RestaurantEntity entityRestaurant = restaurantRepository.findByIdAndIdOwner(plate.getId_restaurant(), plate.getId_owner());

        if(entityRestaurant==null) {
            throw new ValidatePlateException("No existe un restaurante asociado a ese id de propietario");
        }

        CategoryEntity categoryEntity = categoryRequestMapper.toCategoryEntity(category);


        PlateEntity plateEntity = plateEntityMapper.toEntity(plate);
        plateEntity.setRestaurant(entityRestaurant);
        plateEntity.setCategory(categoryEntity);
        plateRepository.save(plateEntity);
    }
    public void editPlate(Plate plate) throws ValidatePlateException {
         Optional<PlateEntity> plateEntityFind = plateRepository.findById(plate.getId());

        if(plateEntityFind.isEmpty()){
            throw new ValidatePlateException("no se encontro plato con ese id");
        }
        plateEntityFind.get().setDescription(plate.getDescription());
        plateEntityFind.get().setPrice(plate.getPrice());
        plateRepository.save(plateEntityFind.get());
    }

    @Override
    public void editStatusPlate(Plate plate) throws ValidatePlateException {
        Optional<PlateEntity> plateEntityFind = plateRepository.findById(plate.getId());
        if(plateEntityFind.isEmpty()){
            throw new ValidatePlateException("no se encontro plato con ese id");
        }
        RestaurantEntity entityRestaurant = restaurantRepository.findByIdAndIdOwner(plateEntityFind.get().getRestaurant().getId(), plate.getId_owner());

        if(entityRestaurant==null) {
            throw new ValidatePlateException("No existe un restaurante asociado a ese id de propietario");
        }
        plateEntityFind.get().setActive(plate.getActive());
        plateRepository.save(plateEntityFind.get());
    }
}
