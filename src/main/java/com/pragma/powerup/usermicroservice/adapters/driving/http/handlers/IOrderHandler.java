package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;


public interface IOrderHandler {
    void saveOrder(OrderRequestDto orderRequestDto,String token ) throws ValidatePlateException, ValidateOrderException;
}
