package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;

public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;

    public PlateUseCase(IPlatePersistencePort platePersistencePort) {
        this.platePersistencePort = platePersistencePort;
    }

    @Override
    public void savePlate(Plate plate) throws ValidatePlateException {
        plate.setActive(true);
        platePersistencePort.savePlate(plate);
    }
    @Override
    public void editPlate(Plate plate) throws ValidatePlateException {
        platePersistencePort.editPlate(plate);
    }


}
