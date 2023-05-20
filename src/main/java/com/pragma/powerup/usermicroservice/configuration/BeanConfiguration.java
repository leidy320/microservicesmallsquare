package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.PlateMyslqAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.RestaurantMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.PlateUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.RestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;

    @Bean
    public IRestaurantServicePort restaurantServicePort(){return new RestaurantUseCase(restaurantPersistencePort());
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
}
