package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderFinishDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrdersToAssing;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderPlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.apirest.IUserRestTemplate;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateOrderException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ValidatePlateException;
import com.pragma.powerup.usermicroservice.domain.model.*;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPllatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserRestaurantPersistencePort;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IPlatePersistencePort platePersistencePort;
    private final IOrderPllatePersistencePort orderPlatePersistencePort;

    private  final IUserRestaurantPersistencePort userRestaurantPersistencePort;

    private  final IUserRestTemplate userRestTemplate;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IPlatePersistencePort platePersistencePort, IOrderPllatePersistencePort orderPlatePersistencePort, IUserRestaurantPersistencePort userRestaurantPersistencePort, IUserRestTemplate userRestTemplate) {
        this.orderPersistencePort = orderPersistencePort;
        this.platePersistencePort = platePersistencePort;
        this.orderPlatePersistencePort = orderPlatePersistencePort;
        this.userRestaurantPersistencePort = userRestaurantPersistencePort;
        this.userRestTemplate = userRestTemplate;
    }

    @Override
    public void saveOrder(Order order, OrderRequestDto orderRequestDto) throws ValidatePlateException, ValidateOrderException {
        Restaurant restaurant= validateIfExistsPlateInRestaurant(orderRequestDto);
        validateOrdersStatus(order.getIdClient());
        order.setStatus("PENDIENTE");
        order.setDate(new Date());
        order.setRestaurant(restaurant);
        Order orderSaved = orderPersistencePort.saveOrder(order);

        OrderPlate orderPlate = new OrderPlate();
        orderPlate.setOrder(orderSaved);
        saveOrderPlate(orderRequestDto, orderPlate);
    }

    @Override
    public List<Order> getOrderByIdRestaurant(Long idEmployee, int page, int pagesize, String status) throws ValidateOrderException {
        UserRestaurant userRestaurant =  userRestaurantPersistencePort.getUserRestaurantByIdEmploye(idEmployee);
        return orderPersistencePort.getOrderByIdRestaurant(userRestaurant.getIdRestaurant(),  page,  pagesize,  status);
    }

    @Override
    public void assingEmployeeToOrder(List<OrdersToAssing> ordersToAssing, Long idEmployee) throws ValidateOrderException {
        UserRestaurant userRestaurant =  userRestaurantPersistencePort.getUserRestaurantByIdEmploye(idEmployee);
        for(OrdersToAssing oneOrdersToAssing : ordersToAssing) {
            Order order = orderPersistencePort.findByIdAndRestaurantId(oneOrdersToAssing.getIdOrder(), userRestaurant.getIdRestaurant());
            order.setIdChef(idEmployee);
            order.setStatus("EN_PREPARACION");
            orderPersistencePort.assingEmployee(order);
        }
    }

    @Override
    public void notifyToClientOrderFinish(OrderFinishDto orderFinishDto) throws ValidateOrderException {
       Order order = orderPersistencePort.findByid(orderFinishDto.getIdOrder());
       if(order == null)
       {
           throw  new ValidateOrderException("no se encontro un plato con ese id");
       }
       order.setStatus("LISTO");
       orderPersistencePort.saveOrder(order);
       String phone= userRestTemplate.getPhoneById(order.getIdClient());
        // Find your Account Sid and Token at twilio.com/console
        final String ACCOUNT_SID = "AC2eb684b8699595e3f5ad7b157f318f74";
        final String AUTH_TOKEN = "51a2715fa8bc50413ef5ba942f1885ed";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phone),
                new com.twilio.type.PhoneNumber("+14065055994"),
                "pedido listo").create();
        System.out.println(message.getSid());


    }

    private void saveOrderPlate(OrderRequestDto orderRequestDto, OrderPlate orderPlate) throws ValidatePlateException {
        for(OrderPlateRequestDto orderPlateRequestDTO : orderRequestDto.getListplates()) {
            Plate plate = platePersistencePort.getPlateById(orderPlateRequestDTO.getId_plate());
            orderPlate.setPlate(plate);
            orderPlate.setQuantity(orderPlateRequestDTO.getQuantity());
            orderPlatePersistencePort.saveOrderPlate(orderPlate);
        }
    }

    private Restaurant validateIfExistsPlateInRestaurant(OrderRequestDto orderRequestDto) {
        Plate plate= null;
        for(OrderPlateRequestDto orderPlate : orderRequestDto.getListplates()) {
            plate= platePersistencePort.findByIdAndIdRestaurant(
                    orderPlate.getId_plate(), orderRequestDto.getIdRestaurant());
        }
        return plate.getRestaurant();
        
    }
    private  void validateOrdersStatus(Long idClient) throws ValidateOrderException {
         orderPersistencePort.findByIdClientAndStatus(idClient);
    }
}
