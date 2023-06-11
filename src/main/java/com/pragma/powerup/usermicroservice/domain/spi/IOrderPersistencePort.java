package com.pragma.powerup.usermicroservice.domain.spi;



import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import org.springframework.data.jpa.repository.Query;

public interface IOrderPersistencePort {
    Order saveOrder(Order order) ;

    void  findByIdClientAndStatus(Long idClient) throws ValidateOrderException;
}
