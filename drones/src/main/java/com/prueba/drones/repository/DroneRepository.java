package com.prueba.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.prueba.drones.model.Drone;

@Component
public interface DroneRepository extends CrudRepository<Drone, String> {

    @Query("SELECT d FROM Drone d LEFT JOIN FETCH d.droneMedications WHERE d.serialNumber = ?1")
    Drone findBySerialNumberWithMedications(String serialNumber);

    @Query("SELECT d FROM Drone d WHERE d.model = ?1")
    List<Drone> findByModel(String model);
}
