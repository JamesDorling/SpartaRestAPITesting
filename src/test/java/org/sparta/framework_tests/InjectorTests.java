package org.sparta.framework_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;
import org.sparta.framework.ConnectionManager;
import org.sparta.framework.Injector;

public class InjectorTests {
    @Test
    @DisplayName("Injector creates a CurrentWeatherDTO")
    void injectorReturnsCorrectType() {
        try (MockedStatic<ConnectionManager> connection = Mockito.mockStatic(ConnectionManager.class)) {
            connection.when(ConnectionManager::getConnection).thenReturn("http://localhost:8080");
            Assertions.assertInstanceOf(TraineeDTO.class, Injector.injectDTO(ConnectionManager.getConnection(), DTOEnum.TRAINEE));
        }
    }
}
