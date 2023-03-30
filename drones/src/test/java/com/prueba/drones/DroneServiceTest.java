package com.prueba.drones;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.prueba.drones.controller.dto.DroneRequestDto;
import com.prueba.drones.enums.DroneError;
import com.prueba.drones.enums.DroneState;
import com.prueba.drones.exception.InvalidInputException;
import com.prueba.drones.model.Drone;
import com.prueba.drones.service.DroneService;

@SpringBootTest
class DroneServiceTest {

    @Autowired
    private DroneService droneService;

    @Test
    public void testRegisterDrone() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setSerialNumber("1234567890");
        droneRequest.setModel("Lightweight");
        droneRequest.setWeightLimit(500);
        droneRequest.setBatteryCapacity(100);
        droneRequest.setState(DroneState.IDLE);
        Drone registeredDrone = droneService.registerDrone(droneRequest);

        Assertions.assertEquals("1234567890", registeredDrone.getSerialNumber());
        Assertions.assertEquals("Lightweight", registeredDrone.getModel());
        Assertions.assertEquals(500, registeredDrone.getWeightLimit());
        Assertions.assertEquals(100, registeredDrone.getBatteryCapacity());
        Assertions.assertEquals(DroneState.IDLE, registeredDrone.getState());
    }

    @Test
    public void testRegisterDroneWitoutDroneState() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setSerialNumber("1234567890");
        droneRequest.setModel("Lightweight");
        droneRequest.setWeightLimit(500);
        droneRequest.setBatteryCapacity(100);
        Drone registeredDrone = droneService.registerDrone(droneRequest);

        Assertions.assertEquals("1234567890", registeredDrone.getSerialNumber());
        Assertions.assertEquals("Lightweight", registeredDrone.getModel());
        Assertions.assertEquals(500, registeredDrone.getWeightLimit());
        Assertions.assertEquals(100, registeredDrone.getBatteryCapacity());
        Assertions.assertEquals(DroneState.IDLE, registeredDrone.getState());
    }



    @Test
    public void testMessageRegisterDroneWithInvalidSerialNumber() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setSerialNumber("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        droneRequest.setModel("Lightweight");
        droneRequest.setWeightLimit(500);
        droneRequest.setBatteryCapacity(100);
        droneRequest.setState(DroneState.IDLE);

        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(droneRequest);
        });

        Assertions.assertEquals(DroneError.INVALID_SERIAL_NUMBER.getMessage(), exception.getErrorMessages().get(0));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }



    // serial number (100 characters max);
    @Test
    public void testRegisterDroneWithInvalidSerialNumber() {
        DroneRequestDto drone = new DroneRequestDto();
        // more than 100 characters
        drone.setSerialNumber("Serial12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        drone.setModel("Lightweight");
        drone.setWeightLimit(500);
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ((InvalidInputException) exception).getStatus());
    }

    @Test
    public void testRegisterDroneWithInvalidModel() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Ultralight"); // invalid model
        drone.setWeightLimit(500);
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals(DroneError.INVALID_DRONE_MODEL.getMessage(), exception.getErrorMessages().get(0));
    }

    @Test
    public void testRegisterDroneWithInvalidWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(1000); // more than 500 grams
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals(1, exception.getErrorMessages().size());
        Assertions.assertEquals(DroneError.EXCEEDED_DRONE_WEIGHT_LIMIT.getMessage(), exception.getErrorMessages().get(0));
    }

    @Test
    public void testRegisterDroneWithInvalidLessWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(-100); // less than 0 grams
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals(1, exception.getErrorMessages().size());
        Assertions.assertEquals(DroneError.NEGATIVE_DRONE_WEIGHT.getMessage(), exception.getErrorMessages().get(0));
    }

    @Test
    public void testRegisterDroneWithInvalidZeroWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(0); // 0 grams
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        Assertions.assertEquals(1, exception.getErrorMessages().size());
        Assertions.assertEquals(DroneError.MISSING_DRONE_WEIGHT.getMessage(), exception.getErrorMessages().get(0));
    }




    @Test
    public void testRegisterDroneWithInvaliLessdWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(-100); // less than 0 grams
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        InvalidInputException invalidInputException = (InvalidInputException) exception;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, invalidInputException.getStatus());
        Assertions.assertTrue(
                invalidInputException.getErrorMessages().contains(DroneError.NEGATIVE_DRONE_WEIGHT.getMessage()));
    }

    @Test
    public void testRegisterDroneWithCerodWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(0); // 0 grams
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        InvalidInputException invalidInputException = (InvalidInputException) exception;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, invalidInputException.getStatus());
        Assertions.assertTrue(
                invalidInputException.getErrorMessages().contains(DroneError.MISSING_DRONE_WEIGHT.getMessage()));
    }

    @Test
    public void testRegisterDroneWithNulldWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        InvalidInputException invalidInputException = (InvalidInputException) exception;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, invalidInputException.getStatus());
        Assertions.assertTrue(
                invalidInputException.getErrorMessages().contains(DroneError.MISSING_DRONE_WEIGHT.getMessage()));
    }

    @Test
    public void testRegisterDroneWithInvaliNegativedWeightLimit() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(-100); // more than 500 grams
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        InvalidInputException invalidInputException = (InvalidInputException) exception;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, invalidInputException.getStatus());
        Assertions.assertTrue(
                invalidInputException.getErrorMessages().contains(DroneError.NEGATIVE_DRONE_WEIGHT.getMessage()));
    }

    // Value between 0 - 100
    @Test
    public void testRegisterDroneWithInvalidBatteryCapacity() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(500);
        drone.setBatteryCapacity(-10); // negative battery capacity
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        InvalidInputException invalidInputException = (InvalidInputException) exception;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, invalidInputException.getStatus());
        Assertions.assertTrue(
                invalidInputException.getErrorMessages().contains(DroneError.INVALID_BATTERY_CAPACITY.getMessage()));
    }

    @Test
    public void testRegisterDroneWithInvalidBatteryValueCapacity() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(500);
        drone.setBatteryCapacity(120); // battery capacity more than 100%
        drone.setState(DroneState.IDLE);
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            droneService.registerDrone(drone);
        });
        InvalidInputException invalidInputException = (InvalidInputException) exception;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, invalidInputException.getStatus());
        Assertions.assertTrue(
                invalidInputException.getErrorMessages().contains(DroneError.INVALID_BATTERY_CAPACITY.getMessage()));
    }

    @Test
    public void testLoadDroneWithMedications() {
        // Test case for loading a drone with medications

    }

    @Test
    public void testGetLoadedMedicationsForDrone() {
        // Test case for getting loaded medications for a drone

    }
}
