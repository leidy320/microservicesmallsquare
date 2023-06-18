package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrdersToAssing;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListOrderResponseDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;

import java.util.List;


public interface IOrderHandler {
    void saveOrder(OrderRequestDto orderRequestDto,String token ) throws ValidatePlateException, ValidateOrderException;
    List<ListOrderResponseDto> getOrderByIdRestaurant(String token, int page, int pagesize, String status) throws ValidateOrderException;
    void assingEmployeeToOrder(List<OrdersToAssing> ordersToAssing, String token) throws ValidateOrderException;
}
