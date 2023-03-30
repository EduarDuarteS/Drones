package com.prueba.drones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.drones.controller.dto.DroneResponseDto;
import com.prueba.drones.model.Drone;
import com.prueba.drones.controller.dto.DroneRequestDto;

import com.prueba.drones.service.DroneService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prueba.drones.controller.dto.ErrorDto;
import com.prueba.drones.controller.dto.ErrorResponseDto;
import com.prueba.drones.exception.InvalidInputException;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<DroneResponseDto> registerDrone(@RequestBody DroneRequestDto droneRequestDto) {
        Drone drone = droneService.registerDrone(droneRequestDto);
        DroneResponseDto droneResponseDto = new DroneResponseDto(drone.getSerialNumber(), drone.getModel(),
                drone.getWeightLimit(), drone.getBatteryCapacity(), drone.getState());
        return new ResponseEntity<>(droneResponseDto, HttpStatus.CREATED);
    }

    @RestControllerAdvice
    public class ExceptionHandlerController {
        @ExceptionHandler(InvalidInputException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponseDto handleInvalidInputException(InvalidInputException ex) {
            List<ErrorDto> errors = new ArrayList<>();
            for (String errorMessage : ex.getErrorMessages()) {
                errors.add(new ErrorDto(errorMessage, "INVALID_INPUT"));
            }
            return new ErrorResponseDto(errors);
        }
    }

}
