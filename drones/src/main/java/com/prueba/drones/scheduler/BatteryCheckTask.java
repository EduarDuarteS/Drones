package com.prueba.drones.scheduler;

import java.time.LocalDateTime;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prueba.drones.model.Drone;
import com.prueba.drones.model.DroneBatteryHistory;
import com.prueba.drones.repository.DroneRepository;

import jakarta.transaction.Transactional;

@Component
public class BatteryCheckTask implements Runnable {

    @Autowired
    private DroneRepository droneRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BatteryCheckTask.class);

    @Override
    @Transactional
    public void run() {

        // Get all drones
        Iterable<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            // Check drone battery level
            int batteryLevel = drone.getBatteryCapacity();
            if (batteryLevel < 25) { // If battery level is less than 25%, create history/audit event

                LOGGER.warn("Drone with Serial Number {} has battery level below 25%.", drone.getSerialNumber());

                DroneBatteryHistory batteryHistory = new DroneBatteryHistory(drone, batteryLevel, LocalDateTime.now());
                drone.addDroneBatteryHistory(batteryHistory);

                droneRepository.save(drone);

            }
        }
    }

}
