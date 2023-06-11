package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.ITokenUtils;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final ITokenUtils tokenUtils;
    @Override
    public void saveOrder(OrderRequestDto orderRequestDto, String token) throws ValidatePlateException, ValidateOrderException {
        Order order = new Order();
        String idClient = tokenUtils.getIdByToken(token);
        order.setIdClient(Long.valueOf(idClient));
        order.setIdChef(orderRequestDto.getIdChef());

        orderServicePort.saveOrder(order, orderRequestDto);
    }
}
