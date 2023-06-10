package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) throws ValidatePlateException {
        Order order = new Order();
        order.setIdClient(orderRequestDto.getIdClient());
        order.setIdChef(orderRequestDto.getIdChef());



        orderServicePort.saveOrder(order, orderRequestDto);
    }
}
