package com.prueba.drones.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


import com.prueba.drones.model.Drone;

@Component
public interface DroneRepository extends CrudRepository<Drone, String> {

    
}
