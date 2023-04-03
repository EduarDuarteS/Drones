package com.prueba.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.prueba.drones.model.Medication;

@Component
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
