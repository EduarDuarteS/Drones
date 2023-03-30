package com.prueba.drones.repository;

import org.springframework.data.repository.CrudRepository;
import com.prueba.drones.model.Drone;

public interface DroneRepository extends CrudRepository<Drone, Long> {

}
