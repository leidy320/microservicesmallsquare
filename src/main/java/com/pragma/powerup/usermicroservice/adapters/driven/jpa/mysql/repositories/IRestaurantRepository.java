package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository  extends JpaRepository<RestaurantEntity, Long> {

    RestaurantEntity findByIdAndIdOwner(Long id, String id_owner);
    Page<RestaurantEntity> findAll(Pageable pageable);

}
