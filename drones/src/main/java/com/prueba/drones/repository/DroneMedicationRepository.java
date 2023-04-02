package com.prueba.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.prueba.drones.model.DroneMedication;

@Component
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {
}
