package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
    Optional<PlateEntity> findById(Long id);
    Page<PlateEntity> findByCategoryIdAndRestaurantIdAndActiveTrue(Long idCategory, Long idRestaurant, Pageable pageable);


    PlateEntity findByIdAndRestaurantId(long id, long idRestaurant);
}

