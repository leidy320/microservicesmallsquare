package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByIdClientAndStatusIn(Long id, List<String> statuses);
    Page<OrderEntity> findByStatusAndRestaurantId(String Status, Long idRestaurant, Pageable pageable);

    Optional<OrderEntity> findByIdAndRestaurantId(Long id, Long restaurantId);
}
