package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrdersToAssing;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderPlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.*;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPllatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserRestaurantPersistencePort;

import java.util.Date;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IPlatePersistencePort platePersistencePort;
    private final IOrderPllatePersistencePort orderPlatePersistencePort;

    private  final IUserRestaurantPersistencePort userRestaurantPersistencePort;



    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IPlatePersistencePort platePersistencePort, IOrderPllatePersistencePort orderPlatePersistencePort, IUserRestaurantPersistencePort userRestaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.platePersistencePort = platePersistencePort;
        this.orderPlatePersistencePort = orderPlatePersistencePort;
        this.userRestaurantPersistencePort = userRestaurantPersistencePort;
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

    @Override
    public List<Order> getOrderByIdRestaurant(Long idEmployee, int page, int pagesize, String status) throws ValidateOrderException {
        UserRestaurant userRestaurant =  userRestaurantPersistencePort.getUserRestaurantByIdEmploye(idEmployee);
        return orderPersistencePort.getOrderByIdRestaurant(userRestaurant.getIdRestaurant(),  page,  pagesize,  status);
    }

    @Override
    public void assingEmployeeToOrder(List<OrdersToAssing> ordersToAssing, Long idEmployee) throws ValidateOrderException {
        UserRestaurant userRestaurant =  userRestaurantPersistencePort.getUserRestaurantByIdEmploye(idEmployee);
        for(OrdersToAssing oneOrdersToAssing : ordersToAssing) {
            Order order = orderPersistencePort.findByIdAndRestaurantId(oneOrdersToAssing.getIdOrder(), userRestaurant.getIdRestaurant());
            order.setIdChef(idEmployee);
            order.setStatus("EN_PREPARACION");
            orderPersistencePort.assingEmployee(order);
        }
    }

    private void saveOrderPlate(OrderRequestDto orderRequestDto, OrderPlate orderPlate) throws ValidatePlateException {
        for(OrderPlateRequestDto orderPlateRequestDTO : orderRequestDto.getListplates()) {
            Plate plate = platePersistencePort.getPlateById(orderPlateRequestDTO.getId_plate());
            orderPlate.setPlate(plate);
            orderPlate.setQuantity(orderPlateRequestDTO.getQuantity());
            orderPlatePersistencePort.saveOrderPlate(orderPlate);
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
