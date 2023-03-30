package com.prueba.drones.model;

import jakarta.persistence.Entity;

import com.prueba.drones.enums.DroneState;

import jakarta.persistence.*;

@Entity
public class Drone {

    public Drone() {
    }

    @Id
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight_limit")
    private double weightLimit;

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

    public Drone(String serialNumber, String model, double weightLimit, double batteryCapacity, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Drone(String serialNumber, String model, double weightLimit, double batteryCapacity) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }


}
