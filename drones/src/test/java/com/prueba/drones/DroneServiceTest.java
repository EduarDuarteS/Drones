package com.prueba.drones;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.drones.controller.dto.DroneRequestDto;
import com.prueba.drones.enums.DroneState;
import com.prueba.drones.model.Drone;
import com.prueba.drones.service.DroneService;

@SpringBootTest
class DroneServiceTest {

    @Autowired
    private DroneService droneService;

    @Test
    public void testRegisterDrone() {
        DroneRequestDto drone = new DroneRequestDto();
        drone.setSerialNumber("1234567890");
        drone.setModel("Lightweight");
        drone.setWeightLimit(500);
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        Drone registeredDrone = droneService.registerDrone(drone);
        Assertions.assertNotNull(registeredDrone.getSerialNumber());
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
