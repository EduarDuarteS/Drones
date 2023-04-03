package com.prueba.drones.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import com.prueba.drones.enums.DroneState;

import jakarta.persistence.*;

@Entity
public class Drone {

    public Drone() {
        this.droneBatteryHistories = new ArrayList<>();
        this.droneMedications = new ArrayList<>();
    }

    public Drone(String serialNumber, String model, double weightLimit, int batteryCapacity, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.droneBatteryHistories = new ArrayList<>();
        this.droneMedications = new ArrayList<>();
    }

    public Drone(String serialNumber, String model, double weightLimit, int batteryCapacity) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.droneBatteryHistories = new ArrayList<>();
        this.droneMedications = new ArrayList<>();
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
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<DroneMedication> droneMedications;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<DroneBatteryHistory> droneBatteryHistories;

    public void addDroneBatteryHistory(DroneBatteryHistory droneBatteryHistory) {
        droneBatteryHistories.add(droneBatteryHistory);
    }

    public void removeDroneBatteryHistory(DroneBatteryHistory droneBatteryHistory) {
        droneBatteryHistories.remove(droneBatteryHistory);
    }

    public List<DroneBatteryHistory> getDroneBatteryHistories() {
        return droneBatteryHistories;
    }

    public void addDroneMedication(DroneMedication droneMedication) {
        droneMedications.add(droneMedication);
    }

    public void removeDroneMedication(DroneMedication droneMedication) {
        droneMedications.remove(droneMedication);
    }

    public List<DroneMedication> getDroneMedications() {
        return droneMedications;
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

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getWeightLoaded() {
        return weightLoaded;
    }

    public void setWeightLoaded(double weightLoaded) {
        this.weightLoaded = weightLoaded;
    }

    public int getBatteryCapacity() {
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
