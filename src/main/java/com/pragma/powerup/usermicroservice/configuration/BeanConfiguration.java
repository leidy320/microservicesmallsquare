package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.PlateMyslqAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.RestaurantMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserRestaurantMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRestaurantRepository;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserRestaurantPersistencePort;
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


    @Bean
    public IRestaurantServicePort restaurantServicePort(){return new RestaurantUseCase(restaurantPersistencePort(), userRestaurantPersistencePort());
    }
    @Bean
    public IPlateServicePort plateServicePort(){return new PlateUseCase(platePersistencePort());
    }

    @Bean
    public IPlatePersistencePort platePersistencePort() {
        return new PlateMyslqAdapter(plateRepository, restaurantRepository,  plateEntityMapper);
    }
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }
    @Bean
    public IUserRestaurantPersistencePort userRestaurantPersistencePort() {
        return new UserRestaurantMysqlAdapter(userRestaurantRepository, userRestaurantEntityMapper, restaurantRepository);
    }
}
