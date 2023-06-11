package com.pragma.powerup.usermicroservice.domain.api;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;


public interface IOrderServicePort {
    void saveOrder(Order order, OrderRequestDto orderRequestDto) throws ValidatePlateException, ValidateOrderException;
}
