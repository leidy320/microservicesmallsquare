package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {

    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderRepository.save(orderRequestMapper.toOrderEntity(order));
        System.out.println("******** orderEntity: "+ orderEntity.getId());
        return orderRequestMapper.toOrder(orderEntity);
    }
}
