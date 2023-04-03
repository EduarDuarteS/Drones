package com.prueba.drones.controller.dto.dronRequestDTOs;

import com.prueba.drones.enums.DroneState;
import com.prueba.drones.model.Drone;

public class DroneRequestDto {

    private String serialNumber;

    private String model;

    private double weightLimit;

    private double batteryCapacity;

    private DroneState state;

    public DroneRequestDto() {
    }

    public DroneRequestDto(String serialNumber, String model, double weightLimit, double batteryCapacity,
            DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public DroneRequestDto(Drone drone) {
        this.serialNumber = drone.getSerialNumber();
        this.model = drone.getModel();
        this.weightLimit = drone.getWeightLimit();
        this.batteryCapacity = drone.getBatteryCapacity();
        this.state = drone.getState();
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

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
