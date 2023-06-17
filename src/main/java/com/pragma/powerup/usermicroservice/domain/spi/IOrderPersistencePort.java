package com.pragma.powerup.usermicroservice.domain.spi;



import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    Order saveOrder(Order order) ;

    void  findByIdClientAndStatus(Long idClient) throws ValidateOrderException;
    List<Order> getOrderByIdRestaurant(Long idRestaurant, int page, int pagesize, String status ) throws ValidateOrderException;

}
