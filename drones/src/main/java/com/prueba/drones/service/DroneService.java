package com.prueba.drones.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.drones.model.Drone;
import com.prueba.drones.repository.DroneRepository;
import com.prueba.drones.controller.dto.DroneRequestDto;
import com.prueba.drones.enums.DroneState;
import com.prueba.drones.exception.InvalidInputException;

import com.prueba.drones.validator.RegisterDroneValidators;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public Drone registerDrone(DroneRequestDto droneRequestDto) {
        List<String> errors = RegisterDroneValidators.validate(droneRequestDto);
        if (!errors.isEmpty()) {
            throw new InvalidInputException(errors);
        }
        DroneState state = droneRequestDto.getState() != null ? droneRequestDto.getState() : DroneState.IDLE;
        Drone drone = new Drone(droneRequestDto.getSerialNumber(), droneRequestDto.getModel(),
                droneRequestDto.getWeightLimit(),
                droneRequestDto.getBatteryCapacity(), state);
        return droneRepository.save(drone);
    }

    public List<Drone> getAllDrones() {
        return (List<Drone>) droneRepository.findAll();
    }

    public Optional<Drone> getDroneById(Long id) {
        return droneRepository.findById(id);
    }

    public void saveDrone(Drone drone) {
        droneRepository.save(drone);
    }

    public void deleteDrone(Long id) {
        droneRepository.deleteById(id);
    }

}
