package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrdersToAssing;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListOrderResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.ITokenUtils;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final ITokenUtils tokenUtils;
    private  final IOrderRequestMapper orderRequestMapper;
    @Override
    public void saveOrder(OrderRequestDto orderRequestDto, String token) throws ValidatePlateException, ValidateOrderException {
        Order order = new Order();
        String idClient = tokenUtils.getIdByToken(token);
        order.setIdClient(Long.valueOf(idClient));
        order.setIdChef(orderRequestDto.getIdChef());

        orderServicePort.saveOrder(order, orderRequestDto);
    }

    @Override
    public List<ListOrderResponseDto> getOrderByIdRestaurant(String token, int page, int pagesize, String status) throws ValidateOrderException {

        Long idEmployee = Long.valueOf(tokenUtils.getIdByToken(token));
        List<Order> orderlist = orderServicePort.getOrderByIdRestaurant( idEmployee,  page,  pagesize,  status);
        List<ListOrderResponseDto> listOrderResponseDtos = orderRequestMapper.toListOrderResponseDto( orderlist);

        return  listOrderResponseDtos;
    }

    @Override
    public void assingEmployeeToOrder(List<OrdersToAssing> ordersToAssing, String token) throws ValidateOrderException {
        Long idEmployee = Long.valueOf(tokenUtils.getIdByToken(token));
        orderServicePort.assingEmployeeToOrder(ordersToAssing, idEmployee);
    }
}
