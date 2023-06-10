package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.OrderPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IOrderPlateRepository;
import com.pragma.powerup.usermicroservice.domain.model.OrderPlate;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPllatePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderPlateMysqlAdapter implements IOrderPllatePersistencePort {

    private final IOrderPlateRepository orderPlateRepository;
    private final OrderPlateEntityMapper plateEntityMapper;

    @Override
    public void saveOrderPlate(OrderPlate orderPlate) {
        orderPlateRepository.save(plateEntityMapper.toOrderPlateEntity(orderPlate));
    }

}
