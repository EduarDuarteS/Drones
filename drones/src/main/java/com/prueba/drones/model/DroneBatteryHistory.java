package com.prueba.drones.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import jakarta.persistence.*;

@Entity
@Table(name = "drone_battery_history")
public class DroneBatteryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serial_number")
    private Drone drone;

    @NotNull
    @Column(name = "battery_level", nullable = false)
    private Integer batteryLevel;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public DroneBatteryHistory() {
    }

    public DroneBatteryHistory(Drone drone, Integer batteryLevel, LocalDateTime timestamp) {
        this.drone = drone;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
