package com.pragma.powerup.usermicroservice.domain.api;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrdersToAssing;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;

import java.util.List;


public interface IOrderServicePort {
    void saveOrder(Order order, OrderRequestDto orderRequestDto) throws ValidatePlateException, ValidateOrderException;
    List<Order> getOrderByIdRestaurant(Long idEmployee, int page, int pagesize, String status) throws ValidateOrderException;
    void assingEmployeeToOrder(List<OrdersToAssing> ordersToAssing, Long idEmployee) throws ValidateOrderException;
}
