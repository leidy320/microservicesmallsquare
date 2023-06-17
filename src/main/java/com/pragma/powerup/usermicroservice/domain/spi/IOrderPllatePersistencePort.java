package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.OrderPlate;

public interface IOrderPllatePersistencePort {

    void saveOrderPlate(OrderPlate orderPlate);



}
