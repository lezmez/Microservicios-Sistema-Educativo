package com.microservice.registration;

import com.microservice.registration.dto.RegistrationDto;
import com.microservice.registration.dto.RegistrationResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegistrationServiceTest {

    @MockBean
    private RegistrationServiceClient registrationServiceClient;

    @Test
    public void testRegisterUser () {
        // Configura el DTO de entrada
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserId("123");
        registrationDto.setSubjectId("456");

        // Configura la respuesta esperada
        RegistrationResponseDto expectedResponse = new RegistrationResponseDto();
        expectedResponse.setId("789");
        expectedResponse.setUser (new RegistrationResponseDto.UserDto());
        expectedResponse.getUser ().setId("123");
        expectedResponse.getUser ().setNombre("John Doe");
        expectedResponse.setSubject(new RegistrationResponseDto.SubjectDto());
        expectedResponse.getSubject().setId("456");
        expectedResponse.getSubject().setSubjectName("Mathematics");

        // Simula la llamada al cliente Feign
        when(registrationServiceClient.registerUser (registrationDto)).thenReturn(expectedResponse);

        // Llama al método y verifica la respuesta
        RegistrationResponseDto actualResponse = registrationServiceClient.registerUser (registrationDto);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @FeignClient(name = "registration-service", url = "http://localhost:8080")
    public interface RegistrationServiceClient {
        @PostMapping("/register")
        RegistrationResponseDto registerUser (@RequestBody RegistrationDto registrationDto);
    }
}