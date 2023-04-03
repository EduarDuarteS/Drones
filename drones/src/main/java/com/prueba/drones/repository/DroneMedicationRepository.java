package com.prueba.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.prueba.drones.model.DroneMedication;

@Component
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {
    List<DroneMedication> findByDroneSerialNumber(String serialNumber);

    @Query("SELECT dm FROM DroneMedication dm JOIN FETCH dm.medications WHERE dm.drone.serialNumber = :serialNumber")
    DroneMedication findByDroneSerialNumberWithMedications(@Param("serialNumber") String serialNumber);
}
