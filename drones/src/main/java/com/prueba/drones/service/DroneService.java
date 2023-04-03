package com.prueba.drones.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.drones.model.*;
import com.prueba.drones.repository.DroneRepository;
import com.prueba.drones.controller.dto.dronRequestDTOs.DroneRequestDto;
import com.prueba.drones.controller.dto.medicineLoadDTOs.MedicationDTO;
import com.prueba.drones.enums.DroneState;
import com.prueba.drones.exception.InvalidInputException;
import com.prueba.drones.exception.InvalidInputLoadDrone;
import com.prueba.drones.mappers.MedicationMapper;
import com.prueba.drones.validator.RegisterDroneValidators;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneService.class);

    public Drone registerDrone(DroneRequestDto droneRequestDto) {
        List<String> errors = RegisterDroneValidators.validate(droneRequestDto);
        if (!errors.isEmpty()) {
            throw new InvalidInputException(errors);
        }
        DroneState state = droneRequestDto.getState() != null ? droneRequestDto.getState() : DroneState.IDLE;
        Drone drone = new Drone(droneRequestDto.getSerialNumber(), droneRequestDto.getModel(),
                droneRequestDto.getWeightLimit(),
                droneRequestDto.getBatteryCapacity(), state);
        return this.droneRepository.save(drone);
    }

    @Transactional
    public void loadMedicines(String droneId, List<MedicationDTO> medications) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();

            // Set drone state to LOADED
            drone.setState(DroneState.LOADED);

            List<Medication> validMedications = medications.stream()
                    .map(MedicationMapper::mapToEntity)
                    .collect(Collectors.toList());

            double totalWeight = validMedications.stream().mapToDouble(Medication::getWeight).sum();
            if (totalWeight > drone.getWeightLimit()) {
                throw new InvalidInputException("Total weight of medications exceeds drone's weight limit");
            }

            DroneMedication droneMedication = new DroneMedication(drone, validMedications, 2);

            // Set drone medications to the newly loaded medications
            drone.addDroneMedication(droneMedication);

            droneRepository.save(drone);
            // droneMedicationRepository.save(droneMedication);
            LOGGER.info("Drone medication saved with medications: {}", droneMedication);

        } else {
            throw new InvalidInputLoadDrone("Drone not found with id: " + droneId);
        }
    }

    public List<Drone> getAllDrones() {
        return (List<Drone>) this.droneRepository.findAll();
    }

    public Optional<Drone> getDroneById(String id) {
        return this.droneRepository.findById(id);
    }

    public void saveDrone(Drone drone) {
        droneRepository.save(drone);
    }

    public void deleteDrone(String id) {
        this.droneRepository.deleteById(id);
    }

}
