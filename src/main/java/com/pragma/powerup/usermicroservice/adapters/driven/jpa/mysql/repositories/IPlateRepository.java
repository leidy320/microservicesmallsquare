package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
    Optional<PlateEntity> findById(Long id);
    //Page<PlateEntity> findAllByIdCategory(Long idCategory, Pageable pageable);
}
