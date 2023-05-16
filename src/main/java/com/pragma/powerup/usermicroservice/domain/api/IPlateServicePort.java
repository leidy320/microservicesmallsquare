package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.Plate;


public interface IPlateServicePort {
    void savePlate(Plate plate);
}
