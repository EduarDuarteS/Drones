package com.prueba.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prueba.drones.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
