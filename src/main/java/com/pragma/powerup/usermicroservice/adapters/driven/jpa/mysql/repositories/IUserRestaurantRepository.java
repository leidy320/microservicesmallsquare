package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;


import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRestaurantRepository extends JpaRepository<UserRestaurantEntity, Long> {
    UserRestaurantEntity findByIdUser(Long idEmployee);
}
