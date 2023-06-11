package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderPlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.model.OrderPlate;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPllatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;

import java.util.Date;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IPlatePersistencePort platePersistencePort;
    private final IOrderPllatePersistencePort orderPllatePersistencePort;



    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IPlatePersistencePort platePersistencePort, IOrderPllatePersistencePort orderPllatePersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.platePersistencePort = platePersistencePort;
        this.orderPllatePersistencePort = orderPllatePersistencePort;
    }

    @Override
    public void saveOrder(Order order, OrderRequestDto orderRequestDto) throws ValidatePlateException, ValidateOrderException {
        Restaurant restaurant= validateIfExistsPlateInRestaurant(orderRequestDto);
        validateOrdersStatus(order.getIdClient());
        order.setStatus("PENDIENTE");
        order.setDate(new Date());
        order.setRestaurant(restaurant);
        Order orderSaved = orderPersistencePort.saveOrder(order);

        OrderPlate orderPlate = new OrderPlate();
        orderPlate.setOrder(orderSaved);
        saveOrderPlate(orderRequestDto, orderPlate);
    }

    private void saveOrderPlate(OrderRequestDto orderRequestDto, OrderPlate orderPlate) throws ValidatePlateException {
        for(OrderPlateRequestDto orderPlateRequestDTO : orderRequestDto.getListplates()) {
            Plate plate = platePersistencePort.getPlateById(orderPlateRequestDTO.getId_plate());
            orderPlate.setPlate(plate);
            orderPlate.setQuantity(orderPlateRequestDTO.getQuantity());
            orderPllatePersistencePort.saveOrderPlate(orderPlate);
        }
    }

    private Restaurant validateIfExistsPlateInRestaurant(OrderRequestDto orderRequestDto) {
        Plate plate= null;
        for(OrderPlateRequestDto orderPlate : orderRequestDto.getListplates()) {
            plate= platePersistencePort.findByIdAndIdRestaurant(
                    orderPlate.getId_plate(), orderRequestDto.getIdRestaurant());
        }
        return plate.getRestaurant();
        
    }
    private  void validateOrdersStatus(Long idClient) throws ValidateOrderException {
         orderPersistencePort.findByIdClientAndStatus(idClient);
    }
}
