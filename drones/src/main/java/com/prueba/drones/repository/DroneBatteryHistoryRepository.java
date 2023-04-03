package com.prueba.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.prueba.drones.model.DroneBatteryHistory;

@Component
public interface DroneBatteryHistoryRepository extends JpaRepository<DroneBatteryHistory, Long> {

    List<DroneBatteryHistory> findByDroneSerialNumber(String serialNumber);
}
