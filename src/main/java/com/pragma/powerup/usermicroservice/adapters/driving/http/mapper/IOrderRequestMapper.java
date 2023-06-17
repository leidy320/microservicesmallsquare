package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListOrderResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ListPlateResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

    Order toOrder(OrderEntity orderEntity);

    OrderEntity toOrderEntity(Order order);
    List<ListOrderResponseDto> toListOrderResponseDto(List<Order> listorder);

}
