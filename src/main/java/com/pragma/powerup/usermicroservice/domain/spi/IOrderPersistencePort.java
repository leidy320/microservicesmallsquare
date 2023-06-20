package com.pragma.powerup.usermicroservice.domain.spi;



import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    Order saveOrder(Order order) ;

    void  findByIdClientAndStatus(Long idClient) throws ValidateOrderException;
    List<Order> getOrderByIdRestaurant(Long idRestaurant, int page, int pagesize, String status ) throws ValidateOrderException;
    Order findByIdAndRestaurantId(Long id, Long restaurantId) throws ValidateOrderException;
    void assingEmployee(Order order) throws ValidateOrderException;

    Order findByid(Long id);

}
