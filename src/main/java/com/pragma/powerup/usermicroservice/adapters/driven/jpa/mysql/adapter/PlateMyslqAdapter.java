package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;

import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PlateMyslqAdapter implements IPlatePersistencePort {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    @Override
    public void savePlate(Plate plate) {

        plateRepository.save(plateEntityMapper.toEntity(plate));
    }
}
