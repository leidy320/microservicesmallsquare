package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateCategoryException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;

import java.util.List;

public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;


    public PlateUseCase(IPlatePersistencePort platePersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.platePersistencePort = platePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void savePlate(Plate plate) throws ValidatePlateException, ValidateCategoryException {
        Category category = categoryPersistencePort.findById(plate.getId_category());
        plate.setActive(true);
        platePersistencePort.savePlate(plate, category);
    }
    @Override
    public void editPlate(Plate plate) throws ValidatePlateException {
        platePersistencePort.editPlate(plate);
    }

    @Override
    public void editStatusPlate(Plate plate) throws ValidatePlateException {
        platePersistencePort.editStatusPlate(plate);
    }

    @Override
    public List<Plate> getPlate(int page, int pageSize, Long idCategory) throws ValidatePlateException, ValidateCategoryException {
        categoryPersistencePort.findById(idCategory);
        return platePersistencePort.getPlate(page, pageSize, idCategory);
    }


}
