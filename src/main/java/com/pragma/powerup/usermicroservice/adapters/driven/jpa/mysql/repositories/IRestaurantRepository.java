package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IRestaurantRepository  extends JpaRepository<RestaurantEntity, Long> {

   // @Query("SELECT * FROM restaurant  WHERE id = :id and id_owner = :id_owner")
    RestaurantEntity findByIdAndIdOwner(Long id, String id_owner);


}
