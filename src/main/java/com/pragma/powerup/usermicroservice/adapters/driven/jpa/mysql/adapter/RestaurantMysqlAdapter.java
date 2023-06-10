package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public void
    saveRestaurant(Restaurant restaurant) {

        /*if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()) {
            System.out.println("entr el if  y genera error");
            throw new RestaurantAlreadyExistsException();
        }*/

        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }


    @Override
    public List<Restaurant> getRestaurant(int page, int pageSize) throws ValidateRestaurantException {

        Sort sort= Sort.by(Sort.Direction.ASC,"name");
        Pageable pageable = PageRequest.of(page,pageSize,sort);


        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll(pageable).toList();
        if(restaurantEntities.isEmpty()){
            throw  new ValidateRestaurantException("No se encontro  restaurantes");
        }
        return restaurantEntityMapper.toListRestaurant(restaurantEntities);
    }

    /*@Override
    public Restaurant GetRestaurantByIdAndIdOwner(Long id, Long id_owner) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findByIdAndIdOwner(id, id_owner));
    }*/
}
