package com.prueba.drones.model;

import jakarta.persistence.Entity;

import java.util.List;

import com.prueba.drones.enums.DroneState;

import jakarta.persistence.*;

@Entity
public class Drone {

    public Drone() {
    }

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight_limit")
    private double weightLimit;

    @Column(name = "weight_loaded")
    private double weightLoaded;

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<DroneMedication> droneMedications;

    public void addDroneMedication(DroneMedication droneMedication) {
        droneMedications.add(droneMedication);
        droneMedication.setDrone(this);
    }

    public void removeDroneMedication(DroneMedication droneMedication) {
        droneMedications.remove(droneMedication);
        droneMedication.setDrone(null);
    }

    public List<DroneMedication> getDroneMedications() {
        return droneMedications;
    }

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

    public double getWeightLoaded() {
        return weightLoaded;
    }

    public void setWeightLoaded(double weightLoaded) {
        this.weightLoaded = weightLoaded;
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
