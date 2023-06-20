package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IOrderEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {

    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderRepository orderRepository;
    private  final IOrderEntityMapper orderEntityMapper;
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

    @Override
    public List<Order> getOrderByIdRestaurant(Long idRestaurant, int page, int pagesize, String status) throws ValidateOrderException {
        Sort sort= Sort.by(Sort.Direction.ASC,"status");
        Pageable pageable = PageRequest.of(page,pagesize,sort);


        List<OrderEntity> orderEntities = orderRepository.findByStatusAndRestaurantId(status, idRestaurant, pageable).toList();
        return orderEntityMapper.toListOrder(orderEntities);
    }

    @Override
    public Order findByIdAndRestaurantId(Long id, Long restaurantId) throws ValidateOrderException {
        Optional<OrderEntity> orderEntity = orderRepository.findByIdAndRestaurantId(id, restaurantId);

        if(!orderEntity.isPresent()){
            throw new ValidateOrderException("No se encontro una orden asociada a ese idRestaurant");
        }

        return orderEntityMapper.toOrder(orderEntity.get());
    }

    @Override
    public void assingEmployee(Order order) throws ValidateOrderException {
        OrderEntity orderEntity = orderRequestMapper.toOrderEntity(order);
        orderRepository.save(orderEntity);
    }

    @Override
    public Order findByid(Long id) {
        return orderEntityMapper.toOrder(orderRepository.findById(id).get());
    }

}
