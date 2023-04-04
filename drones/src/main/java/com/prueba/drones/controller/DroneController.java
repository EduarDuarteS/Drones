package com.prueba.drones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.drones.model.Drone;
import com.prueba.drones.service.DroneService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prueba.drones.controller.dto.dronRequestDTOs.DroneRequestDto;
import com.prueba.drones.controller.dto.dronRequestDTOs.DroneResponseDto;
import com.prueba.drones.controller.dto.dronRequestDTOs.ErrorDto;
import com.prueba.drones.controller.dto.dronRequestDTOs.ErrorResponseDto;
import com.prueba.drones.controller.dto.medicineLoadDTOs.MedicationDTO;
import com.prueba.drones.exception.DroneNotFoundException;
import com.prueba.drones.exception.InvalidInputException;
import com.prueba.drones.exception.InvalidInputLoadDrone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneController.class);

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PutMapping("/{droneId}/load")
    public ResponseEntity<Void> loadDrone(@PathVariable("droneId") String droneId,
            @RequestBody List<MedicationDTO> medications) {
        LOGGER.info("Loading medications PUT");

        LOGGER.debug("Loading medications for drone with ID: {}", droneId);
        droneService.loadMedicines(droneId, medications);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<DroneResponseDto> registerDrone(@RequestBody DroneRequestDto droneRequestDto) {
        LOGGER.info("Recibiendo petición post");

        Drone drone = droneService.registerDrone(droneRequestDto);
        DroneResponseDto droneResponseDto = new DroneResponseDto(drone.getSerialNumber(), drone.getModel(),
                drone.getWeightLimit(), drone.getBatteryCapacity(), drone.getState());
        return new ResponseEntity<>(droneResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{droneId}/medicationItems")
    public ResponseEntity<List<MedicationDTO>> getLoadedMedicationItemsForDrone(
            @PathVariable("droneId") String droneId) {
        try {
            List<MedicationDTO> medicationItems = droneService.getLoadedMedicationItemsForDrone(droneId);
            return ResponseEntity.ok(medicationItems);
        } catch (DroneNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/availableForLoading")
    public ResponseEntity<List<DroneResponseDto>> getAvailableDronesForLoading() {
        try {
            List<DroneResponseDto> drones = droneService.getAvailableDronesForLoading();
            return ResponseEntity.ok(drones);
        } catch (DroneNotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/{serialNumber}/battery")
    public ResponseEntity<Map<String, Object>> checkDroneBatteryLevel(@PathVariable String serialNumber) {
        try {
            int batteryLevel = droneService.getBatteryLevel(serialNumber);
            Map<String, Object> response = new HashMap<>();
            response.put("Serial", serialNumber);
            response.put("battery", batteryLevel);
            response.put("message", "Drone battery level: " + batteryLevel + "%");
            return ResponseEntity.ok(response);
        } catch (DroneNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
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

        @ExceptionHandler(InvalidInputLoadDrone.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponseDto handleInvalidInputLoadDrone(InvalidInputLoadDrone ex) {
            List<ErrorDto> errors = new ArrayList<>();
            for (String errorMessage : ex.getErrorMessages()) {
                errors.add(new ErrorDto(errorMessage, "INVALID_INPUT_LOAD_DRONE"));
            }
            return new ErrorResponseDto(errors);
        }
    }
}
