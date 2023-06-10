package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.OrderPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.*;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.*;
import com.pragma.powerup.usermicroservice.domain.usecase.OrderUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.PlateUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.RestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final IUserRestaurantRepository userRestaurantRepository;
    private final IUserRestaurantEntityMapper userRestaurantEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderRepository orderRepository;
    private final IOrderPlateRepository orderPlateRepository;
    private final OrderPlateEntityMapper orderPlateEntityMapper;

    @Bean
    public IRestaurantServicePort restaurantServicePort(){return new RestaurantUseCase(restaurantPersistencePort(), userRestaurantPersistencePort());
    }
    @Bean
    public IPlateServicePort plateServicePort(){return new PlateUseCase(platePersistencePort(), categoryPersistencePort());
    }
    @Bean
    public IOrderServicePort orderServicePort(){return new OrderUseCase(orderPersistencePort(),platePersistencePort(),orderPllatePersistencePort()) ;
    }

    @Bean
    public IPlatePersistencePort platePersistencePort() {
        return new PlateMyslqAdapter(plateRepository, restaurantRepository,  plateEntityMapper, categoryRequestMapper);
    }
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }
    @Bean
    public IUserRestaurantPersistencePort userRestaurantPersistencePort() {
        return new UserRestaurantMysqlAdapter(userRestaurantRepository, userRestaurantEntityMapper, restaurantRepository);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryMyslqAdapter(categoryRepository, categoryRequestMapper);
    }
    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderMysqlAdapter(orderRequestMapper, orderRepository);
    }
    @Bean
    public IOrderPllatePersistencePort orderPllatePersistencePort() {
        return new OrderPlateMysqlAdapter(orderPlateRepository, orderPlateEntityMapper);
    }
}
