package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {

    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderRepository.save(orderRequestMapper.toOrderEntity(order));
        return orderRequestMapper.toOrder(orderEntity);
    }

    @Override
    public void findByIdClientAndStatus(Long idClient) throws ValidateOrderException {
        List<String>  statusNotAllow =new ArrayList<>(Arrays.asList("PENDIENTE", "LISTO", "EN_PREPARACION"));
        Optional<OrderEntity> findByIdAndValidateStatus= orderRepository.findByIdClientAndStatusIn(idClient, statusNotAllow);
        if(findByIdAndValidateStatus.isPresent()){
            throw new ValidateOrderException("ya hay otro pedido por este cliente");
        }
    }

}
