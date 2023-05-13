package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.exceptions.ValidateRestaurantException;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantUseCaseTest {
    @InjectMocks
    private  RestaurantUseCase restaurantUseCase;
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void  shouldGenerateExceptionByNit() {
        String nit= "sdfsd47557";
        assertThrows(ValidateRestaurantException.class, () -> restaurantUseCase.validateNit(nit), "El Campo nit  solo acepta numeros");
    }
    @Test
    void  shouldGenerateExceptionByPhone() {
        String phone= "sdfs587547557";
        assertThrows(ValidateRestaurantException.class, () -> restaurantUseCase.validatePhone(phone), "El numero de telefono no debe superar los 13 caracteres, ni debe contener letras");
    }
    @Test
    void  shouldGenerateExceptionByName() {
        String name= "225742757";
        assertThrows(ValidateRestaurantException.class, () -> restaurantUseCase.validateName(name), "El nombre no debe contener solo numeros");
    }

}
