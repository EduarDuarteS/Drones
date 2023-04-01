package com.prueba.drones;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.drones.controller.dto.dronRequestDTOs.DroneRequestDto;
import com.prueba.drones.enums.DroneError;
import com.prueba.drones.enums.DroneState;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DroneControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Should return CREATED status when sending a valid DroneRequestDto")
    void registerDrone_ReturnsCreatedStatus_WhenValidDroneRequest() throws Exception {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setSerialNumber("1234567890");
        droneRequest.setModel("Lightweight");
        droneRequest.setWeightLimit(500);
        droneRequest.setBatteryCapacity(100);
        droneRequest.setState(DroneState.IDLE);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/drones",
                droneRequest,
                Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should return BAD REQUEST status when sending an invalid DroneRequestDto")
    void registerDrone_ReturnsBadRequestStatus_WhenInvalidDroneRequest() throws Exception {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setSerialNumber(
                "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        droneRequest.setModel("Lightweight");
        droneRequest.setWeightLimit(500);
        droneRequest.setBatteryCapacity(100);
        droneRequest.setState(DroneState.IDLE);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/drones",
                droneRequest,
                Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should throw InvalidInputException with correct message when sending an invalid DroneRequestDto")
    void registerDrone_ThrowsException_WhenInvalidDroneRequest() throws Exception {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setSerialNumber(
                "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        droneRequest.setModel("Lightweight");
        droneRequest.setWeightLimit(500);
        droneRequest.setBatteryCapacity(100);
        droneRequest.setState(DroneState.IDLE);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/drones",
                droneRequest,
                String.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
        JsonNode errorMessages = responseJson.get("errors").get(0).get("message");
        Assertions.assertEquals(DroneError.INVALID_SERIAL_NUMBER.getMessage(), errorMessages.asText());

    }

}
